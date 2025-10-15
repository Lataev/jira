'''
Multiple Issue Picker
Поиск запросов с таким-же значение поля asset_field или значением атрибута parent = asset_field
'''


import com.atlassian.jira.issue.Issue


{ Issue issue, String configuredJql ->


    if (issue.getCustomFieldValue(11111)) { // asset_field
        def asset_key = issue.getCustomFieldValue(11111)[0].objectKey
        return """project = PROJ AND status = Open AND issuetype != Subtask AND ('asset_field' = $asset_key OR 'asset_field' in iqlFunction("parent = $asset_key"))"""
    }

}
