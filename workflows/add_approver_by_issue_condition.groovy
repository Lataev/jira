'''
Если id Тип запроса клиента = 123, если в поле выбора 16000 выбрана опция 15000, админ устанавливает в поле согласующего пользователя user.
'''

def requestTypeID = issue.requestType.id
if ( issue.requestType.id == 123) {
    if (issue.getCustomFieldValue(16000).optionId == 15000) {
        Users.runAs('admin') {
            issue.set {
                setCustomFieldValue('Согласующий', 'user')
            }
        }
    }
}