'''
Сравнение строковых полей для использования в jql
'''

string[] keys = selectIssues("project = proj and resolution is EMPTY"); // jql: issue in silJQLList("compare_fields.sil")

string[] matchingIssues;

for(string k in keys) {
    string creator = k.creator;
    string reporter = k.reporter;
    
    boolean arraysMatch = true;
    
    // Если размер или содержимое массивов не совпадают, добавление в список
    if (creator != reporter) {
        matchingIssues += k;
    }
}

return matchingIssues;
