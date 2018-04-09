$(function (){
    //console.log("13");
    //获取登录用户的笔记本列表
   getNoteBooks();
   //退出登录点击事件
    $('#logout').click(logout);
});

//退出登录
function logout() {
    delCookie("userId");
    window.location.href="../../log_in.html";
};

//获取登录用户的笔记本列表
function getNoteBooks() {
    //console.log("14");
    var userId=getCookie("userId");
    var data={"userId":userId};
    $.ajax({
        url:"noteBook/findNoteBook.do",
        type:"post",
        dataType:"json",
        data:data,
        success:function(result){
            //console.log("15");
            if(result.state==0){
                //console.log("16");
                //console.log(result.data);
                var books=result.data;
                $('#note_book_ul').empty();
                for(i in books){
                    //console.log(books[i]);
                    showNoteBook(books[i]);
                }
            }
            if(result.state==3){
                alert(result.message);
            }

        },
        error:function(e){
            alert("通信失败");
        }
    });
};

//为ul添加笔记本子节点
function showNoteBook(book) {
    //var noteBook=book;
    //console.log(book.name);
    var li=$("<li class='online'>" + "<a id='" +
       book.id+"' onclick='showNote();'><i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"
        + book.name+
        "</a></li>");
    //console.log(li);
    $("#note_book_ul").append(li);

};

function showNote(bookId) {
    //var noteId=$(bookId.target).attr('id');
    console.log($(this).html());

};