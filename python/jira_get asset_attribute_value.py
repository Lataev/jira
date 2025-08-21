'''
Получение значения атрибута Актива
'''


from atlassian import Insight
from secret import auth_data


insight = Insight(
        url=auth_data.insight().server,
        token=auth_data.insight().token,
        verify_ssl=False
        )

user_asset = insight.get_object('KEY-4462')

for attribute in user_asset['attributes']:
    if attribute['objectTypeAttributeId'] == 3333: # id атрибута
        print(attribute['objectAttributeValues'][0]['value'])

insight.close()