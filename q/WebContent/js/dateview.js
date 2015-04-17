var dgoutObject;
var dgMonHead = new Array(12);    		   //����������ÿ���µ��������
	dgMonHead[0] = 31; dgMonHead[1] = 28; dgMonHead[2] = 31; dgMonHead[3] = 30; dgMonHead[4]  = 31; dgMonHead[5]  = 30;
	dgMonHead[6] = 31; dgMonHead[7] = 31; dgMonHead[8] = 30; dgMonHead[9] = 31; dgMonHead[10] = 30; dgMonHead[11] = 31;
var dgmeizzTheYear=new Date().getFullYear(); //������ı����ĳ�ʼֵ
var dgmeizzTheMonth=new Date().getMonth()+1; //�����µı����ĳ�ʼֵ
var dgmeizzWDay=new Array(37);               //����д���ڵ�����

function dgsetday(controls) //��������
{
  //if (arguments.length >  2){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
  //if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ���");return;}
  /*
  var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ���
  var thei  = tt.clientHeight;  //TT�ؼ�����ĸ�
  var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
  var ttyp  = tt.type;          //TT�ؼ�������
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;*/
  dgoutObject = controls;
  //event.returnValue=false;
}

/*
function document.onclick() //������ʱ�رոÿؼ�
{ 
  with(window.event.srcElement){ 
	if (tagName != "INPUT" && getAttribute("Author")==null){
		if(dgoutObject!=null){
			dgoutObject.onfocus=dsaveValue;
			dgoutObject.select(); 
		}
		document.all.meizzDateLayer.style.display="none";
	}
  }
}*/

function dgmeizzWriteHead(yy,mm){  //�� head ��д�뵱ǰ��������
  document.all.meizzYearHead.innerText  = yy;
  document.all.meizzMonthHead.innerText = mm;
}

/*
function dgtmpSelectYearInnerHTML(strYear) //��ݵ�������
{
  if (strYear.match(/\D/)!=null){alert("���������������֣�");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("���ֵ���� 1000 �� 9999 ֮�䣡");return;}
  var n = m - 10;
  if (n < 1000) n = 1000;
  if (n + 26 > 9999) n = 9974;
  var s = "<select name=tmpSelectYear style='font-size: 12px' "
	 s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
	 s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
	 s += "dgmeizzTheYear = this.value; dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = n; i < n + 26; i++)
  {
	if (i == m)
	   {selectInnerHTML += "<option value='" + i + "' selected>" + i + "��" + "</option>\r\n";}
	else {selectInnerHTML += "<option value='" + i + "'>" + i + "��" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}

function dgtmpSelectMonthInnerHTML(strMonth) //�·ݵ�������
{
  if (strMonth.match(/\D/)!=null){alert("�·�������������֣�");return;}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select name=tmpSelectMonth style='font-size: 12px' "
	 s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
	 s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
	 s += "dgmeizzTheMonth = this.value; dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++)
  {
	if (i == m)
	   {selectInnerHTML += "<option value='"+i+"' selected>"+i+"��"+"</option>\r\n";}
	else {selectInnerHTML += "<option value='"+i+"'>"+i+"��"+"</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectMonthLayer.style.display="";
  document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectMonth.focus();
}*/

function dgcloseView(){               //������ڵĹر�
	window.close();
}

/*
function document.onkeydown(){
	if (window.event.keyCode==27)
		document.all.meizzDateLayer.style.display="none";
}*/

function dgIsPinYear(year){            //�ж��Ƿ���ƽ��
	if (0==year%4&&((year%100!=0)||(year%400==0)))
		return true;
	else 
		return false;
}

function dgGetMonthCount(year,month){  //�������Ϊ29��
	var c=dgMonHead[month-1];
	if((month==2)&&dgIsPinYear(year))c++;
	return c;
}

function dgGetDOW(day,month,year){     //��ĳ������ڼ�
	var dt=new Date(year,month-1,day).getDay()/7; 
	return dt;
}

function dgmeizzPrevY(){  //��ǰ�� Year
	if(dgmeizzTheYear > 999 && dgmeizzTheYear <10000){
		dgmeizzTheYear--;
	}
	else{
		alert("��ݳ�����Χ��1000-9999����");
	}
	dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);
}
  
function dgmeizzNextY(){  //��� Year
	if(dgmeizzTheYear > 999 && dgmeizzTheYear <10000){
		dgmeizzTheYear++;
	}
	else{
		alert("��ݳ�����Χ��1000-9999����");
	}
	dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);
}

function dgmeizzToday(){  //Today Button
	dgmeizzTheYear = new Date().getFullYear();
	dgmeizzTheMonth = new Date().getMonth()+1;
	dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);
}

function dgmeizzPrevM(){  //��ǰ���·�
	if(dgmeizzTheMonth>1){
		dgmeizzTheMonth--;
	}
	else{
		dgmeizzTheYear--;
		dgmeizzTheMonth=12;
	}
	dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);
}

function dgmeizzNextM(){ //����·�
	if(dgmeizzTheMonth==12){
		dgmeizzTheYear++;
		dgmeizzTheMonth=1;
	}
	else{
		dgmeizzTheMonth++;
	}
	dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);
}

function dgmeizzSetDay(yy,mm){   //��Ҫ��д����**********
	dgmeizzWriteHead(yy,mm);
	for (var i = 0; i < 37; i++){  //����ʾ�������ȫ�����
		dgmeizzWDay[i]=""
	}; 
	
	var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();  //ĳ�µ�һ������ڼ�
	for (var i = firstday; day1 < dgGetMonthCount(yy,mm)+1; i++){
		dgmeizzWDay[i]=day1;day1++;
	}
	for (var i = 0; i < 37; i++){ 
		var da = eval("document.all.meizzDay"+i);    //��д�µ�һ���µ�������������
		if (dgmeizzWDay[i]!=""){ 
		da.innerHTML = "<b>" + dgmeizzWDay[i] + "</b>";
		
		da.style.backgroundColor = (yy == new Date().getFullYear() &&
		mm == new Date().getMonth()+1 && 
		dgmeizzWDay[i] == new Date().getDate()) ? "#FFD700" : "#ADD8E6";
		
		da.style.cursor="hand";
		}
		else{
			da.innerHTML="";
			da.style.backgroundColor="";
			da.style.cursor="default";
		}
	}
}

function dgmeizzDayClick(n){  //�����ʾ��ѡȡ���ڣ������뺯��*************
	var yy = dgmeizzTheYear;
	var mm = dgmeizzTheMonth;
	if (mm < 10){mm = "0" + mm;}
	if (dgoutObject){
		if (!n) {return;}
		if ( n < 10){n = "0" + n;}
		dgoutObject.value= yy + "/" + mm + "/" + n; //ע�����������������ĳ�����Ҫ�ĸ�ʽ
		dgcloseView();
	}
	else{
		alert("����Ҫ����Ŀؼ����󲢲����ڣ�");
		dgcloseView();
	}
}

dgmeizzSetDay(dgmeizzTheYear,dgmeizzTheMonth);