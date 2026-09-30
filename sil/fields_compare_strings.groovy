'''
Сравнение строковых полей для использования в jql
'''

string[] keys = selectIssues("project = proj and resolution is EMPTY"); // jql: issue in silJQLList("compare_fields.sil")

string[] matchingIssues;

for(string k in keys) {
    string creator = k.creator; // возвращает key вида JIRAUSER1111
    string reporter = k.reporter;
    
    // Если не совпадают, добавление в список
    if (creator != reporter) {
        matchingIssues += k;
    }
}

return matchingIssues;
