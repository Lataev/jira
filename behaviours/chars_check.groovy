'''
Проверка значения текстового поля на содержание недопустимых символов.
'''


def charsRegex = /[^\\\/:\*\?"“<>|]/

def field = getFieldById('customfield_17017')
log.warn(field.value)

def errorChars = field.value.toString().replaceAll(charsRegex, "")
log.warn(errorChars)

if (errorChars) {
    field.setError('Недопустимые символы: ' + errorChars)
}
else {
    field.clearError()
}