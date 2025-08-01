'''
Редактировать запрос из консоли.
'''

def issue = Issues.getByKey('PROJ-111')
Users.runAs('admin') {
    issue.update {
        setAssignee('user')
        setCustomFieldValue(10500, 'value')
        setCustomFieldValue('field_name', 123)

        setSendEmail(false)
    }
}