function uploadfile(quid,dele){
    var width=500;
    var height=255;
    var left=(window.screen.width-width)/2;
    var top=(window.screen.height-height)/2;
    var mybars="copyhistory=no,directories=no,location=yes,menubar=no,";
    mybars=mybars+"resizable=no,scrollbars=yes,status=yes,title=no,toolbar=yes,";
    mybars=mybars+"width="+width+",height="+height+",left="+left+",top="+top;
    window.open("upload010.jsp?djid="+quid+"&dele="+dele,"uploadfile",mybars);
}