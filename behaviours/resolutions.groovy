'''
Ограничение выбора вариантов решения по запросу в зависимости от перехода.
'''

if (action) {

    def done_names = ['Решить запрос', 'Решить'] // Через имя наглядно, но надёжнее через id - done_ids.contains(action.id) 
    if (done_names.contains(actionName)) 
        {
            getFieldById('resolution').setFieldOptions(['Готово'])
        }

    def done_not_names = ['Отменить запрос', 'Отменить']
    if (done_not_names.contains(actionName)) 
        {
            getFieldById('resolution').setFieldOptions(['Отклонён', 'Не будет выполнено', 'Дубликат', 'Не воспроизводится'])
        }

}