$(document).ready(function () {

    $('#startTime').datetimepicker({
        defaultDate: '05/09/2016 08:00:00',
        format: 'DD/MM/YYYY HH:mm:ss'
    });
    $('#startTime-edit').datetimepicker({
        defaultDate: '05/09/2016 08:00:00',
        format: 'DD/MM/YYYY HH:mm:ss'
    });

    $('#loading-image').bind('ajaxStart', function () {
        $(this).hide();
    }).bind('ajaxStop', function () {
        $(this).show();
    });

    $(".editButton").click(function () {
        $('li > a[href="' + "#home" + '"]').tab("show");

    });

    //////////////////
    // Начало. Валидация формы перед отправкой
    $('#aboutClient')
        .bootstrapValidator({
            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: 'Имя не может быть пустым'
                        }
                    }
                },
                last_name: {
                    validators: {
                        notEmpty: {
                            message: 'Фамилия не может быть пустым'
                        }
                    }
                }

            }
        })
        .on('success.form.fv', function (e) {
            // Save the form data via an Ajax request
            e.preventDefault();

            var $form = $(e.target),
                id = $form.find('[name="id"]').val();

            // The url and method might be different in your application
            $.ajax({
                url: '/client/' + id,
                method: 'PUT',
                data: $form.serialize()
            }).success(function (response) {
                // Get the cells
                var $button = $('button[data-id="' + response.id + '"]'),
                    $tr = $button.closest('tr'),
                    $cells = $tr.find('td');

                // Update the cell data
                $cells

                    .eq(1).html(response.name).end()
                    .eq(2).html(response.email).end()
                    .eq(3).html(response.website).end();

                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The user profile is updated');
            });
        });
    // Конец. Валидация формы перед отправкой

    $('.editButton').on('click', function () {
        // Get the record's ID via attribute
        var id = $(this).attr('data-id');
        $('#aboutClient')
            .find('[name="id"]').val("response.id").end();

        $.ajax({
            url: '/client/' + id,
            method: 'GET'
        }).success(function (response) {
            // Populate the form fields with the data returned from server
            console.log('getting data');
            //ДОБАВИТЬ ОБНУЛЕНИЕ ВСЕХ 4 ФОРМ НА ТАБАХ
            $('#aboutClient')
                .find('[name="id"]').val(response.id).end()
                .find('[name="name"]').val(response.fname).end()
                .find('[name="last_name"]').val(response.sname).end()
                .find('[name="email"]').val(response.email).end()
            //.find('[name="website"]').val(response.website).end();
            console.log('[got data] ' + response.email)
            // Show the dialog
            $('#myModal')
                .on('shown.bs.modal', function () {
                    $('#aboutClient')
                        .data('bootstrapValidator').resetForm(); // Reset form
                })
                .on('hide.bs.modal', function (e) {
                    // Bootbox will remove the modal (including the body which contains the login form)
                    // after hiding the modal
                    // Therefor, we need to backup the form
                    ////$('#modal1').hide().appendTo('body');
                })

        });
    });
    ////////////////// Поиск начало

    $(".search").keyup(function () {
        var searchTerm = $(".search").val();
        var listItem = $('.clientsTable tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function (elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".clientsTable tbody tr").not(":containsi('" + searchSplit + "')").each(function (e) {
            $(this).attr('visible', 'false');
        });

        $(".clientsTable tbody tr:containsi('" + searchSplit + "')").each(function (e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.clientsTable tbody tr[visible="true"]').length;
        $('.counter').text('Клиентов найдено: ' + jobCount);

        if (jobCount == '0') {
            $('.no-result').show();
        }
        else {
            $('.no-result').hide();
        }
    });

    ////////////////// Поиск конец

    ////////////////// showCreate
    $(".showCreate").on('click', function () {
        $(".formCreate").toggle(500);

        //$(".formCreate").hide(500);
    });
    ////////////////// showCreate

    /// createNew
    $(".createNew").on('click', function () {
        $(".formCreate").hide(500);

    });
    /// createNew
    $("a[href='#menu1']").on('shown.bs.tab', function (event) {
        var id = $('.editButton').attr('data-id');
        $.ajax({
            url: '/client/' + id + '/trainings',
            method: 'GET'
        }).success(function (data) {
            $('#modal-trainings').find('tbody').children('tr').remove();
            $.each(data, function (i, training) {
                var row = $("<tr>");
                row.append($("<td>" + moment.unix(training.start / 1000).format("DD/MM/YYYY HH:mm") + "</td>"))
                    .append($("<td>" + training.trainerName + "</td>"));
                $('#modal-trainings').find('tbody').append(row);
            })
        });
    });

    $("a[href='#menu2']").on('shown.bs.tab', function (event) {
        var id = $('.editButton').attr('data-id');
        $.ajax({
            url: '/client/' + id + '/purchases',
            method: 'GET'
        }).success(function (data) {
            $('#modal-purchases').find('tbody').children('tr').remove();
            $.each(data, function (i, purchase) {
                var row = $("<tr>");
                row.append($("<td>" + moment.unix(purchase.purchaseDate / 1000).format("DD/MM/YYYY") + "</td>"))
                    .append($("<td>" + purchase.productName + "</td>"))
                    .append($("<td>" + purchase.price + "</td>"))
                    .append($("<td>" + purchase.paid + "</td>"))
                    .append($("<td>" + purchase.discountAmount + "</td>"))
                    .append($("<td>" + purchase.trainerName + "</td>"));
                $('#modal-purchases').find('tbody').append(row);
            })
        });
    });
});
