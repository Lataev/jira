'''
Удаление значения поля непосредственно в БД
'''


import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.ModifiedValue
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder

def issue = Issues.getByKey('PROJ-001')

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def cf = customFieldManager.getCustomFieldObject(12345)
// def cf = customFieldManager.getCustomFieldObjectsByName('field_name').first()

if (cf) {
    def changeHolder = new DefaultIssueChangeHolder()
    cf.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(cf), null), changeHolder)
}