import json
import requests
import argparse

def message(content,at):
    url = 'https://oapi.dingtalk.com/robot/send?access_token='
    pagrem = {
        "msgtype": "text",
        "text": {
            "content": content
        },
        "at":{
            "atMobiles":at,
            "isAtAll": False
        }
    }
    headers = {
        'Content-Type': 'application/json'
    }
    requests.post(url, data=json.dumps(pagrem), headers=headers)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    # Model
    parser.add_argument('--title', type=str, default=256, help='title of program')
    parser.add_argument('--at', default=13453114137, type=int, help='at someone(phone number)')
    parser.add_argument('--time', default='unknown', type=str, help='when did it start?')
    args, unparsed = parser.parse_known_args()
    content = "Msg:program finished, please check the result"
    content = "Msg: program \' {} \' is finished. It was started at {}".format(args.title,args.time)
    message(content,[args.at])

