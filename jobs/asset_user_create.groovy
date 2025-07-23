'''
Создание ассетов для каждого пользователя с атрибутами: Имя, пользователь, статус.
'''

import com.atlassian.jira.component.ComponentAccessor

def userManager = ComponentAccessor.getUserManager()
def users = userManager.getAllApplicationUsers()

users.each { user ->

    if(Assets.search("objectType = Кадры and \"Jira User\" = \"$user.name\"").collect().isEmpty()) // Если не существует ассета с пользователем в поле Jira User.
    {
        if(user.active) // Если пользователь не заблокирован, создаётся ассет со статусом "Активно".
        {
            Assets.create(100) { // 100 - id типа объекта.
                setAttribute('Name', user.displayName.toString()) // Отображаемое имя пользователя записывается в тектовый атрибут Name
                setAttribute('Jira User', user.name.toString()) // Логин пользователя записывается в атрибут типа "пользователь" Jira User 
                setAttribute(123, 'Активно')  // Статус записывается в атрибут типа "Status", ID атрибута берётся из настроек типа ассета.
            }
        }
        else // Если пользователь заблокирован, создаётся ассет со статусом "Закрыто".
        {
            Assets.create(100) { // 100 - id типа объекта
                setAttribute('Name', user.displayName.toString())
                setAttribute('Jira User', user.name.toString())
                setAttribute(123, 'Закрыто')
            }
        }
        
    }

}
