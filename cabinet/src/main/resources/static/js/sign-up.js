var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
});
$(document).ready(function () {

    demo.checkFullPageBackgroundImage();

    setTimeout(function () {
        // after 1000 ms we add the class animated to the login/register card
        $('.card').removeClass('card-hidden');
    }, 700);

    var $validator = $("#wizardForm").validate({
        rules: {
            email: {
                required: true,
                email: true,
                minlength: 5
            },
            first_name: {
                required: false,
                minlength: 5
            },
            last_name: {
                required: false,
                minlength: 5
            },
            website: {
                required: true,
                minlength: 5,
                url: true
            },
            framework: {
                required: false,
                minlength: 4
            },
            cities: {
                required: true

            },
            phone: {
                number: true,
                minlength: 4
            }
        }
    });

    // you can also use the nav-pills-[blue | azure | green | orange | red] for a different color of wizard
    $('#wizardCard').bootstrapWizard({
        tabClass: 'nav  nav-pills',
        nextSelector: '.btn-next, .btn-sms-ver, .btn-phone-ver',
        previousSelector: '.btn-back',
        onNext: function (tab, navigation, index) {
            var $valid = $('#wizardForm').valid();

            if (!$valid) {
                $validator.focusInvalid();
                return false;
            }

            var $total = navigation.find('li').length;
            var $current = index;

            if (index >= $total) {
                //   Autologin?()
            } else if (index == 1) {
                return verifyPhone();
            } else {
                return verifySms();
            }


        },
        onInit: function (tab, navigation, index) {

            //check number of tabs and fill the entire row
            var $total = navigation.find('li').length;
            $width = 100 / $total;

            $display_width = $(document).width();

            if ($display_width < 600 && $total > 3) {
                $width = 50;
            }

            navigation.find('li').css('width', $width + '%');
        },
        onTabClick: function (tab, navigation, index) {
            // Disable the posibility to click on tabs
            return false;
        },
        onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;

            var wizard = navigation.closest('.card-wizard');

            // If it's the last tab then hide the last button and show the finish instead
            if ($current >= $total) {
                $(wizard).find('.btn-next').hide();

                $(wizard).find('.btn-sms-ver').hide();
                $(wizard).find('.btn-tel-ver').hide();
                $(wizard).find('.btn-back').hide();
                $(wizard).find('.btn-finish').show();
            } else if ($current == 1) {
                $(wizard).find('.btn-back').hide();
                $(wizard).find('.btn-sms-ver').hide();

                $(wizard).find('.btn-phone-ver').show();
            } else {

                $(wizard).find('.btn-sms-ver').show();
                $(wizard).find('.btn-back').show();
                // $(wizard).find('.btn-next').show();
                $(wizard).find('.btn-phone-ver').hide();
                $(wizard).find('.btn-finish').hide();
            }
        }
    });
});

function onFinishWizard() {
    var $password = $('#password').val(), $copyPassword = $('#copy-password').val();
    $.ajax({
        type: "post",
        url: "/sign-up/confirm?p=" + btoa($password) + "&c=" + btoa($copyPassword),
        statusCode: {
            200: function () {
                window.location.href = '/user';
                //swal("Отлично!", "Теперь у вас есть учётная запись в личном кабинете", "success");
            },
            400: function (response) {
                swal(":(", response.responseText, "error");
            }
        }
    });

}

function verifySms() {
    //Валидация смс todo
    var $code = $("#code").val(), status;
    $.ajax({
        type: "post",
        url: "/sign-up/verify?code=" + $code,
        statusCode: {
            200: function () {
                status = true;
            },
            400: function () {
                swal(":(", "Мы отправляли Вам другую смс", "error");
                status = false;
            }
        },
        async : false
    });
    return status;
}

function verifyPhone() {
    //Добавить валидацию телефона (проверить, что он есть в базе, можно возвращать имя, чтобы приветствовать пользователя)
    var $phone = $("#phone").val(), status;
    $.ajax({
        type: "post",
        url: "/sign-up/send?phone=" + $phone,
        statusCode: {
            200: function (data) {
                $('#client-name').text(data);
                status = true;
            },
            204: function () {
                swal(":(", "У нас не зарегистрировано такого телефона, пожалуйста, проверьте корректность ввода", "error");
                status = false;
            },
            400: function (response) {
                swal(":(", response.responseText, "error");
                status = false;
            }
        },
        async : false
    });
    return status;
}