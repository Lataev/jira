'''
Сравнение полей массивов для использования в jql
'''

string[] keys = selectIssues("project = proj and resolution is EMPTY"); // jql: issue in silJQLList("compare_fields.sil")

string[] matchingIssues;

for(string k in keys) {
    string[] parts1 = k.customfield_10300;
    string[] parts2 = k.customfield_13604;
    
    boolean arraysMatch = true;
    
    // Сравнение длин массивов
    if (arraySize(parts1) != arraySize(parts2)) {
        arraysMatch = false;
    } else {
        // Поиск элементов parts1 в parts2
        for (string item in parts1) {
            if (arrayFind(parts2, item) == -1) {
                arraysMatch = false;
            }
        }
    }
    
    // Если размер или содержимое массивов не совпадают, добавление в список
    if (!arraysMatch) {
        matchingIssues += k;
    }
}

return matchingIssues;
