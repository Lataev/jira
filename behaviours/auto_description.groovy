'''
Автоматическое заполение описания по шаблону в зависимости от выбранной опции в поле выбора.
Скрипт прописывается в server side script поля customfield_16000.
'''

if (actionName == 'Создать проблему' | actionName == 'Create') {

    def select_field = getFieldById('customfield_16000') // Тип неполадки, поле выбора.
    def description = getFieldById('description') // Описание.

    if (select_field.formValue == '14900') // Неполадки с ПО.
    {
        description.setFormValue('''Добрый день!\n\nНеполадки ПО.\nОписание: ''')
    }

    if (select_field.formValue == '14901') // Неполадки с оборудованием.
    { 
        description.setFormValue('''Добрый день!\n\nНеполадки с оборудованием.\nЧто не работает: ''')
    }

    if (select_field.formValue == '14902') // Недоступен ресурс.
    { 
        description.setFormValue('''Добрый день!\n\nНедоступен ресурс.\nЧто недоступно: ''')
    }

}