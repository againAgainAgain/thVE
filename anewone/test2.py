from bs4 import BeautifulSoup
import requests
import random
import chardet
#随机代理获取的网站
url = 'http://www.xicidaili.com/nn/'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36'
}

#将获取到的代理IP存到ip_list中并返回列表
def get_ip_list(url, headers):
    web_data = requests.get(url, headers=headers)
    soup = BeautifulSoup(web_data.text, 'lxml')
    ips = soup.find_all('tr')
    ip_list = []
    for i in range(1, len(ips)):
        ip_info = ips[i]
        tds = ip_info.find_all('td')
        ip_list.append(tds[1].text + ':' + tds[2].text)
    return ip_list

#从代理IP列表中随机取出一个IP并返回
def get_random_ip(ip_list):
    proxy_list = []
    for ip in ip_list:
        proxy_list.append('http://' + ip)
    proxy_ip = random.choice(proxy_list)
    proxies = {'http': proxy_ip}
    return proxies


if __name__ == '__main__':
    ip_list = get_ip_list(url, headers=headers)
    print(ip_list)
    proxies = get_random_ip(ip_list)
    print(proxies)
    #代理IP的使用
    headers = {
        'User-Agent':'Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3 like Mac OS X; wo-SN) AppleWebKit/535.16.1 (KHTML, like Gecko) Version/4.0.5 Mobile/8B114 Safari/6535.16.1'
    }
    url0 = 'https://movie.douban.com/top250'
    response = requests.get(url0,proxies = proxies, headers=headers)
    # 确定编码方式
    response.encoding = chardet.detect(response.content)['encoding']
    html = response.text

    # lxml解码器
    soup = BeautifulSoup(html, 'lxml')
    # 获取电影信息。首先获取所有的div信息
    div = soup.find(name='div', attrs={'id': 'content'})
    # 拿到所有（div，di=content）信息以后，发现每个电影的信息都在一个li里面，取出所有li
    li_list = div.find_all(name='li')
    li = li_list[0]
    p = li.find('p')
    a = li.find(name='a')
    img = a.find(name='img')
    em = li.find('em')
    s = li.find(name='span', attrs={'class': 'rating_num'})
    src = img.attrs.get('src')

    # file_name = open( 'D:/豆瓣电影TOP250.txt' , 'a' , encoding = chardet.detect( response.content )[ 'encoding' ] )
    # file_name.write(img.attrs.get('alt').strip() + ',' + s.text.strip() + ',' + p.text.strip() + ',' +src.strip() + ',' + a.attrs.get('href').strip() + '\n')
    # file_name.write(img.attrs.get('alt').strip() + ',' + s.text.strip() + ',' + src.strip() + ',' + a.attrs.get('href').strip() + '\n')

    # 进入电影的具体的界面
    film_name = img.attrs.get('alt').strip()
    print(film_name + '\n')
    url1 = a.attrs.get('href').strip()
    print(url1 + '\n')
    # 设置请求头
    # headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)     AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103  Safari/537.36' }
    # 确定编码方式
    response1 = requests.get(url1, headers=headers)
    response1.encoding = chardet.detect(response1.content)['encoding']
    html1 = response1.text
    # 该电影的当前页面中的所有的影评的div
    soup1 = BeautifulSoup(html1, 'lxml')
    div1 = soup1.select('div.review-list')
    reviews_div = div1[0].select("div.main.review-item")
    review = reviews_div[0]
    review_href0 = review.find('h2')
    review_href = review_href0.find('a').attrs.get('href').strip()  # 影评的链接
    review_name = review_href0.find('a').text.strip()  # 影评的名字
    print(review_name)
    print(review_href)

    # 进入影评的链接爬取文字
    response2 = requests.get(review_href, headers=headers)
    # response2.encoding = chardet.detect(response2.content)['encoding']
    response2.encoding = 'utf-8'
    html2 = response2.text
    # 该电影的当前页面中的所有的影评的div
    soup2 = BeautifulSoup(html2, 'lxml')
    div2 = soup2.select('div.review-content.clearfix')
    print(div2[0].stripped_strings)

    file_name = open('D:/' + review_name + '.son', 'a', encoding=chardet.detect(response.content)['encoding'])
    s = ''.join(list(div2[0].stripped_strings))
    file_name.write("{fid:" + str(1) + ",comment:" + s + ",c_src:" + "" + review_href + '\n')