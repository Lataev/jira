'''
Если пользователь в группе due-edit, то он может редактировать дату исполнения запроса.
Остальные могут только установить дату, если она не установлена, но не изменить.
'''

def user = Users.loggedInUser
def due_field = getFieldById('duedate')
def due_field_val = issueContext.dueDate

if (user.isMemberOfGroup('due-edit')) // Если пользователь в группе due-edit.
{
    due_field.setAllowInlineEdit(true)
    due_field.setReadOnly(false) // Разрешить редактирование поля.
} 
else 
{
    if (due_field_val) // Если пользователь не в группе и поле заполено.
    {
        due_field.setAllowInlineEdit(false)
        due_field.setReadOnly(true) // Поле не редактируемое.
    } 
    else // Если пустое.
    {
        due_field.setAllowInlineEdit(true)
        due_field.setReadOnly(false) // Разрешить редактирование поля.
    }
}