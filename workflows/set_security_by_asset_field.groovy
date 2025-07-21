if (issue.getCustomFieldValue(11401)) { // Если поле ассета заполнено.
    def level_id = issue.getCustomFieldValue(11401)[0].getInteger('Security Level') // Берётся атрибут ассета 'Security Level' с заполненным id.
    issue.setSecurityLevelId(level_id) // Устанавливается уровень безопасности по id.
}