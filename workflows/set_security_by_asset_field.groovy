'''
Установка уровня безопасности на основе атрибута 'Security Level' из поля ассета.
'''

if (issue.getCustomFieldValue(11000)) // Если поле ассета заполнено.
{
    def level_id = issue.getCustomFieldValue(11000)[0].getInteger('Security Level') // Берётся атрибут ассета 'Security Level' с заполненным id.
    issue.setSecurityLevelId(level_id) // Устанавливается уровень безопасности по id.
}