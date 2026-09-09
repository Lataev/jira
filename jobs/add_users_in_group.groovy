'''
Добавление пользователей в группу в зависимости от домена email.
'''

import com.atlassian.jira.component.ComponentAccessor

def userManager = ComponentAccessor.getUserManager()
def allUsers = userManager.getAllApplicationUsers() // функция считается устаревшей, но работает
def allUsers2 = Groups.getByName('jira-software-users').members // можно использовать участников самой массовой группы

def group = Groups.getByName("groupName")

allUsers.each { user ->

    def email = user.emailAddress

    if (user.active && (email =~ 'domen1' || email =~ 'domen2') && !user.isMemberOfGroup(group.name)) { 
        group.add(user)
    }

}
