'''
Восстановление последнего значения поля из истории тикета после удаления.
Удобно использовать для массового восстановления.
'''


import com.atlassian.jira.component.ComponentAccessor
def changeHistoryManager = ComponentAccessor.changeHistoryManager

def issue = Issues.getByKey('PROJ-111')
def field = ''

changeHistoryManager.getChangeHistories(issue).reverseEach { history -> // в обратном порядке reverseEach, в прямом each
    
    for (item in history.changeItems) {
        if(item.field == "field_name" && item.oldvalue ) 
        {
            log.warn(item.oldvalue)
            log.warn(item.newvalue)

            if (!field & !item.newvalue)
            {
                Users.runAs('admin') 
                {
                    issue.update 
                    { 
                        setCustomFieldValue('field_name', item.oldvalue.toString()) // для поля ассета, нужно убрать скобки, чтобы получить id: [1..-2][1..-2])
                        setSendEmail(false) // если не нужно отправлять уведомление
                        setEventDispatchOption(EventDispatchOption.DO_NOT_DISPATCH) // если не нужно создавать событие
                    }
                }
                field = item.oldvalue.toString()
            }
        }
    }
}