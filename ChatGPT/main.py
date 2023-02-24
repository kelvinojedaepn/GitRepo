#
# import openai
#
# def askGPT(text):
#     openai.api_key = "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"
#     response = openai.Completion.create(
#         engine = "text-davinci-003",
#         prompt = text,
#         temperature = 0.6,
#         max_tokens = 150,
#     )
#     return print(response.choices[0].text)
#
# def main():
#     while True:
#         print('GPT: Ask me a question\n')
#         myQn = input()
#         askGPT(myQn)
#         print('\n')
#
# main()
# #import openai
# import config
#
# openai.api_key = config.OPENAI_API_KEY if 'OPENAI_API_KEY' in dir(config) else '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'
# print(f'openai.api_key : {openai.api_key}')
#
#
# def openAIQuery(query):
#     response = openai.Completion.create(
#         engine="davinci-instruct-beta-v3",
#         prompt=query,
#         temperature=0.8,
#         max_tokens=200,
#         top_p=1,
#         frequency_penalty=0,
#         presence_penalty=0)
#
#     if 'choices' in response:
#         if len(response['choices']) > 0:
#             answer = response['choices'][0]['text']
#         else:
#             answer = 'Opps sorry, you beat the AI this time'
#     else:
#         answer = 'Opps sorry, you beat the AI this time'
#
#     return answer
#
#
# if __name__ == '__main__':
#     if not openai.api_key:
#         print(f'api_key is not set')
#         exit(0)
#
#     query = 'Generate a keras 3 layer neural network python code for classification'
#     try:
#         response = openAIQuery(query)
#         print(f'Response : {response}')
#     except Exception as e:
#         print(f'Exception : {str(e)}')

import openai
openai.api_key = "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"

while True:
    prompt = input("\n Introduce una pregunta: ")
    if prompt == "xx":
        break
    else:
        completion = openai.Completion.create(engine="text-davinci-003", prompt = prompt, max_tokens= 2048)
        print(completion.choices[0].text)

