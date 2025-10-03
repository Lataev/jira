'''
Массовый перевод запросов в другой статус без:
- отправки email
- создания события
- проверки условий
- проверки прав
- проверки валидаторов
'''


def issues = Issues.search('project = proj AND issuetype = "Задача" AND status in (Отктрыто, "В работе")')

Users.runAs('admin') {
    issues.each { 
        it.transition('Приемка') {
            setSendEmail(false)
            setEventDispatchOption(EventDispatchOption.DO_NOT_DISPATCH)
            transitionOptions { 
                skipConditions()
                skipPermissions()
                skipValidators()
             }
        }
     }
}