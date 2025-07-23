'''
Переход разрешён, если текущий пользователь (Исполнитель или имеет роль Диспетчер) и не участник группы group.
'''

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.security.roles.ProjectRoleManager
 
def assignee = issue.assignee

def projectRoleManager = ComponentAccessor.getComponent(ProjectRoleManager)
def devProjectRole = projectRoleManager.getProjectRole("Диспетчер")

if (
        (
            projectRoleManager.isUserInProjectRole(currentUser, devProjectRole, issue.getProjectObject()) 
        || 
            assignee == currentUser
        ) 
    &&
        !currentUser.isMemberOfGroup('group')
    )
{
    return true
} 
else 
{
    return false
}