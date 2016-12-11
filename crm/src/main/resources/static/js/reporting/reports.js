$(document).ready(function () {
    var $table = $('#table'),
        $filter = $('#filter'),
        $balanceTrainingItem = $('#balance-training-item'),
        $soldSubscriptionsItem = $('#sold-subscriptions-item')

    $balanceTrainingItem.click(function () {
        $table.bootstrapTable({
            url: '/reporting/balance-training/data',
            method: 'get',
            filterControl: true,
            search: true,
            showExport: true,
            toolbar: '#toolbar',
            columns: [
                {
                    field: 'clientName',
                    title: 'Клиент',
                    filterControl: 'input'
                },
                {
                    field: 'productName',
                    title: 'Абонемент',
                    filterControl: 'input'
                },
                {
                    field: 'trainingCount',
                    title: 'Тренировок осталось',
                    filterControl: 'select',
                    sortable: true
                }
            ]
        });
    });

    $soldSubscriptionsItem.click(function () {
        $.get('/reporting/sold-subscriptions/filter', function (data) {
            $filter.replaceWith(data);
        });
    });

    $('#run-report').bind("click", function () {
        $table.bootstrapTable({
            url: '/reporting/sold-subscriptions/data',
            queryParams: function (p) {
              return {
                  from : $('#from').val(),
                  to : $('#to').val(),
                  product_id : $('product_id').val()
              };
            },
            method: 'get',
            filterControl: true,
            search: true,
            showExport: true,
            toolbar: '#toolbar',
            columns: [
                {
                    field: 'clientName',
                    title: 'Клиент',
                    filterControl: 'input'
                },
                {
                    field: 'productName',
                    title: 'Абонемент',
                    filterControl: 'input'
                },
                {
                    field: 'purchaseDate',
                    title: 'Дата продажи',
                    sortable: true
                },
                {
                    field: 'price',
                    title: 'Цена'
                },
                {
                    field: 'payd',
                    title: 'Заплачено клиентом'
                }
            ]
        });
    });

});