'''
Создание ассета с атрибутами пользователя Jira и другого ассета из полей заявки для дальнейшей обработки скриптом.
Атрибуты принимают значение по названию и по id.
Перед получением значения поля ассета, необходимо проверять, что поле заполнено: if (issue.getCustomFieldValue(11500))
'''

def userAssetField = issue.getCustomFieldValue(11500)[0].getString('Jira User') // Возвращает id пользователя вида JIRAUSER11111 из атрибута ассета в поле 11500 (Пользователь)
def jiraUser = Users.getByKey(userAssetField) // Получение пользователя jira.
def assetField = issue.getCustomFieldValue(12500)[0] // Значение другого поля ассета из заявки. (Система)

Assets.create(123) {  // 123 - id типа объекта
    setAttribute('Name', "${jira_user.displayName} \\ ${assetField.name}") // Имя ассета = ФИО \ Название системы.
    setAttribute('Пользователь', jira_user.name.toString()) // пользователь Jira, принимает логин
    setAttribute(1200, assetField.objectKey) // ссылка на другой ассет
    setAttribute(1201, 'Требуется действие') // атрибут Status - выпадающий список
} 
