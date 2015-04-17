function balancevalue(ob){
      var textb= document.all("textb"+ob).value-0;
	  var textc= document.all("textc"+ob).value-0
	  
	  if(textc>textb){
	     alert("ס�������ܴ��ڳ�������!")
		 document.all("textc"+ob).value=""
         return false;
	  }
	   document.all("textc"+ob).disabled=true;
	  
}
function sp_vs(ob){
      var textb= document.all("textb"+ob).value-0;
	  var textc= document.all("textc"+ob).value-0
	  
	  if(textc>textb){
	     alert("ס�������ܴ��ڳ�������!")
		 document.all("textc"+ob).value=""
         return false;
	  }
	   document.all("textc"+ob).disabled=true;
	  
}
function disvalue(ob){
	  document.all("texte"+ob).disabled=true;	  
}
