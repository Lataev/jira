'''
Автоматическое заполение описания по шаблону в зависимости от выбранной опции в поле выбора.
Скрипт прописывается в server side script поля customfield_16000.
'''

if ( action && [1, 11].contains(action.id) ) { // 'Создать проблему', 'Create'

    def select_field = getFieldById('customfield_16000') // Тип неполадки, поле выбора.
    def description = getFieldById('description') // Описание.

    def iss_dict = ['14900':'Добрый день!\n\nНеполадки ПО.\nОписание: ', // Неполадки с ПО.
                   '14901':'Добрый день!\n\nНеполадки с оборудованием.\nЧто не работает: ', // Неполадки с оборудованием.
                   '14902':'Добрый день!\n\nНедоступен ресурс.\nЧто недоступно: ' // Недоступен ресурс.
                ]

    if (select_field) {
        description.setFormValue(iss_dict.get(select_field.formValue, ''))
    }

}