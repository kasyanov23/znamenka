$(document).ready(function () {
    var $table = $('#table'),
        $filter = $('#filter'),
        $balanceTrainingItem = $('#balance-training-item'),
        $soldSubscriptionsItem = $('#sold-subscriptions-item')


    $soldSubscriptionsItem.click(function () {
        $table.bootstrapTable('resetView');
        $.get('/reporting/sold-subscriptions/filter', function (data) {
            $filter.replaceWith(data);
        });
    });

});

function initSoldSubscriptionTable() {
    var $table = $('#table'),
        $filter = $('#filter');

    $table.bootstrapTable({
        url: '/reporting/sold-subscriptions/data',
        queryParams: function (p) {
            return {
                from: $('#from').val(),
                to: $('#to').val(),
                product_id: $('#product_id').val()
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
}