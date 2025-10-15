'''
Calculated (scripted) Text/Html Field
Создание ссылки для поиска запросов по значению текстового поля, которое соответствует названию актива из поля текущего запроса.
'''


if (issue.get("customfield_11111") && issue.get("customfield_22222")) {
    def type = issue.get("customfield_22222")[0].getLabel() // Тип, выбранный пользователем
    def field = issue.get("customfield_11111")[0].getLabel() // Название
    def url_text = type + ' - ' + field
    '<a href="https://jira.domain.com/issues/?filter=11123&jql=project = PROJ AND %22Название%22 ~ %22*'+field+'*%22 ORDER BY created DESC">🔎 Поиск: '+url_text+'</a>'
}