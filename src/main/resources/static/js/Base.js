function getLocalhostPath(){
    var curWwwPath = window.document.location.href;
    var pathname= window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathname);
    return curWwwPath .substring(0,pos);
}

function send( url , method , async, doSuccess, doError ){
    console.log( 'send url= ' + url );
    $.ajax({
        url: url,
        method: method,
        async: async,
        contentType : 'application/json',
        success: doSuccess,
        error : doError
    });
}

function sendGetAsync( url ,doSuccess, doError){
    return send( url , 'GET' , true ,  doSuccess , doError );
}
function sendGet( url , doSuccess, doError){
    console.log( 'sendGet url= ' + url );
    send( url ,'GET' , false , doSuccess , doError );
}

function sendPostAsync( url ,doSuccess, doError){
    return send( url , 'POST' , true ,  doSuccess , doError );
}
function sendPost( url , doSuccess, doError){
    return send( url ,'POST' , false , doSuccess , doError );
}