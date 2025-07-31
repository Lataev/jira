'''
Отображение поля в форме запроса в SD в зависимости от того, заполнен ли атрибут в ассете, связанным с текущим пользователем.
Пользователь заполняет поле лдин раз, псле чего скрипт на переходе заполняет атрибут ассета и пользователь больше не видит это поле в заявках.
Удобно, если нужна массоая информация от пользователей.
В данном примере заполняется руководитель.

'''
def user = Users.loggedInUser.name

'''
По имени поля.
'''
if (Assets.search("""objectType = Пользователи AND "Jira User" = $user AND Руководитель IS NOT EMPTY""").collect().isEmpty()) 
{

    getFieldByName('Руководитель').setHidden(false)
    getFieldByName('Руководитель').setRequired(true)
    log.warn('Руководитель не заполнен ' + user)
} 
else 
{
    getFieldByName('Руководитель').setHidden(true)
    log.warn('Руководитель заполнен: ' + user)
}

'''
По id поля.
'''
if (Assets.search("""objectType = Пользователи AND "Jira User" = $user AND Руководитель IS NOT EMPTY""").collect().isEmpty()) 
{
    getFieldById('12300').setHidden(false)
    getFieldById('12300').setRequired(true)
    log.warn('Руководитель не заполнен ' + user)
} 
else 
{
    getFieldById('12300').setHidden(true)
    log.warn('Руководитель заполнен: ' + user)
}
