function show(){
    var key = document.querySelector('#search').value;

    if ( key.trim().length>0) {
        accessServer();
    }
    else
    {
        alert("请输入检索词");
    }
    document.querySelector('#search').value= " ";

}
function accessServer() {
    const fetchPromise = fetch(
        "http://localhost:8080/javaWeb_war_exploded/result",
    );


    fetchPromise.then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP 请求错误：${response.status}`);
        }
        return response.text();
    }).then((text) => {
        // 判读p标签数量是否超过3个，超过则删除第一个
        if (document.querySelectorAll('#resource p').length >= 2) {
            document.querySelector('#resource p').remove();
        }
        document.querySelector('#resource').innerHTML +=  "<p>" + text + "</p>";
    }).catch((error) => {
        console.error('Fetch 错误:', error);
        document.querySelector('#more').innerHTML = "请求失败，请稍后再试";
    });
}