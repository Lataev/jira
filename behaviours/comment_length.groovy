'''
Ограничение минимальной длины комментария на некоторых переходах.
'''

if ([111, 222].contains(action.id)) { // Если переход с id 111 или 222.

    def comment = getFieldById('comment') // Поле комментария.
    def comment_value = comment.value.toString() // Значение поля комментария.
    def comment_value_clear = comment.value.toString().replaceAll("[^a-zA-Zа-яА-ЯЁё]", "") // Очистка значения поля комментария от всех символов, кроме букв.

    if (comment_value.length() == 0) // Если значение поля комментария пустое, очистка ошибки. При этом, поле должно быть обязательным.
    {
        comment.clearError()
    }
    else if (comment_value_clear.length() < 6) // Иначе, если меньше 6 символов, ошибка.
    {
        comment.setError('Комментарий слишком короткий. Минимальная длина 6 символов.')
    }
    else // Иначе, очистка ошибки.
    {
        comment.clearError()
    }

}