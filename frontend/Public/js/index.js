$(document).ready(function(){
	$(".subtitle").click(function(){
		var id="#"+$(this).attr("id")+"-items";
		$(id).slideToggle("slow");
	});
	/*$(".subtitle").css("background-image",function(){
	alert("url(../images/"+$(this).attr("id")+".png)");
		return "url(../images/"+$(this).attr("id")+".png)";
	});*/
	$(".item").mouseenter(function(){
		$(this).css("color","#000");
	});
	$(".item").mouseleave(function(){
		$(this).css("color","#fff");
	});
});
function transForm(newForm,oldForm){
	document.getElementById(oldForm).style.display='none';
	document.getElementById(newForm).style.display='block';
}
function showForm(formName){
	var target=document.getElementById(formName);
	$(target).slideToggle("slow");
}
function register(form){
	//注册
	if(validateForm(form)){
		alert("success");
		window.open("index.html");
		return true;
	}
	else return false;
}
function login(form){
//登陆
	if(validateForm(form)){
		alert("success");
		window.open("index.html");
		return true;
	}
	else return false;
}
function answer(form){
//提交答案
	if(validateForm(form)){
		alert("success");
		window.open("index.html");
		return true;
	}
	else return false;
}

	function validateForm(theForm){// 若验证通过则返回true
		var disableList=new Array();
		var field = theForm.childNodes; // 将表单中的所有元素放入数组
		//alert(field.length);
		for(var i = 0; i < field.length; i++){			 
			var validatorType=field[i].name;
			if(validatorType!=null){//根据不同类型验证
				var value=trim(field[i].value);
				//alert(field.length+" "+i+"  "+value);
				if(value.length==0&&validatorType!=""){
					alert(field[i].name+"不能为空");
					field[i].focus();
					return false;
				}
				else if(value.length>10000){
					alert(field[i].name+'内容过长');
				}
				var rs=true;
				if(validatorType=="account"){
					rs=validateAccount(field[i]);
				}
				else if(validatorType=="passwd"){
					rs=validatePassword(field[i]);
				}
				else if(validatorType=="checkpasswd"){
					rs=validateCorrespond(document.getElementsByName("passwd")[0],field[i]);
				}
				else if(validatorType=="disable"){//提交表单前disable的按钮
					disableList.length++;
					disableList[disableList.length-1]=field[i];
					continue;
				}
				else if(validatorType=="select"){
					rs=validateSelect(field[i]);
				}
				else if(validatorType=="email"){
					rs=validateEmail(field[i]);
				}
				else if(validatorType=="phone"){
					rs=validatePhone(field[i]);
				}
				else{//一般验证
					var v = field[i].validator; // 获取其validator属性
					if(!v) continue;            // 如果该属性不存在,忽略当前元素
					var reg=new RegExp(v);
					if(reg.test(field[i].value)==false){
						alert(field[i].errorInfo);
						field[i].focus();
						return false;
					}
				}
				if(rs==false){
					return false;
				}
			}
		}
		for(i=0;i<disableList.length;i++){
			disableList[i].disabled=true;
		}
		return true;
	}
	function trim(str){
	  if(str==null) return "";
	  str=str.replace(/(\<|\>|\/|\$|\%|\?|\\|\&|\'|\"|\(|\)|\!|\*|\^)/g,"");
	  if(str.length==0) return "";
	  var i=0,j=str.length-1,c;
	  for(;i<str.length;i++){
		c=str.charAt(i);
		if(c!=' ') break;
	  }
	  for(;j>-1;j--){
		c=str.charAt(j);
		if(c!=' ') break;
	  }
	  if(i>j) return "";
	  str=str.substring(i,j+1);
	  return str;
	}
	function validateAccount(obj){
		var myReg=/^[a-zA-Z0-9]{6,16}$/;
		if(!myReg.test(obj.value)){
			alert(obj.placeholder);
			obj.focus();
			return false;
		}
		else return true;
	}
	function validatePassword(obj){
		var myReg=/^[a-zA-Z0-9]{6,12}$/;
		if(!myReg.test(obj.value)){
			if(obj.placeholder!=null&&obj.placeholder!='')
				alert(obj.placeholder);
			else alert("密码不正确");
			obj.focus();
			return false;
		}
		else return true;
	}
	function validateCorrespond(obj1,obj2){
		if(obj1.value==obj2.value)return true;
		else {
			alert(obj2.placeholder);
			obj2.focus();
			return false;
		}
	}
	function validateSelect(obj){
	//权限第0项提示选择，后面的为选项，此处核对是否选择了非零选项
	  var rs=false;
	  if(obj!=null){
		for(i=1;i<obj.options.length;i++){
		  if(obj.options[i].selected==true){
			return true;
		  }
		}
	  }
	  alert(obj.options[0].text);
	  obj.focus();
	  return rs;
	}
	function validateEmail(email,separator){
	  var em;
	  var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
	  if(separator==null){
		if(myReg.test(email.value)==false){
		  alert("您输入的邮箱有误");
		  email.focus();
		  return false;
		}
	  }
	  else{
		em=email.value.split(separator);
		for(i=0;i<em.length;i++){
		  em[i]=em[i].trim();
		  if(em[i].length>0&&myReg.test(em[i])==false){
			alert("您输入的邮箱有误");
			email.focus();
			return false;
		  }
		}
	  }
	  return true;
	}
	function validatePhone(phone){
		var myReg=/(\+86)?1[3|5|8]\d{9}/;
		if(myReg.test(phone.value)==false){
			alert("您输入的手机号码有误");
			phone.focus();
			return false;
		}
		return true;
	}
	function things_detail(id){
	//获取频道数据
		window.location.href="thing-detail.html";
		return false;
	}