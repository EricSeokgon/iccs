
function TreeList(){
	//openAll이 true일 경우 디스플레이시 모든 폴더를 열어둔다.
	this.openAll = true;
	// 객체의 빠른 접근을 위한 인덱스 테이블
	this.entryMap     = new HashMap();
	// 트리가 생성될 때의 객체의 이름(기본값:treelist)
	this.name           = "treelist"; 
	// 아이템의 갯수
	this.count          = 0;
	// 사용할 이미지 TreeImage의 인스턴스
	this.images         = null;
	// 현재 선택된 객체의 ID
	this.currentID      = -1;
	
	this.paintingDiv = "";

	this.treeHTML = "";

	this.setPaintingDiv = function(div){
		this.paintingDiv = document.getElementById(div);
	}

	// 트리에 필요한 각종 이미지를 설정하는 함수
	this.setOpenAll = function(flag) {
		this.openAll = flag;
	}

	// 트리에 필요한 각종 이미지를 설정하는 함수
	this.setImages = function(obj) {
		this.images = obj;
	}
	
	// 객체를 테이블에 추가한다
	this.setObject = function(objId, obj) {
		this.entryMap.put(objId, obj);
		return obj;
	}

	this.getObject = function(objId) {
		return this.entryMap.get(objId);
	}

	// 트리객체의 오브젝트명을 설정한다
	// 다수의 트리가 한 페이지에 존재할 경우에 각각을 구분해주기 위해서 필요하다
	this.setName = function(name) {
			this.name = name;
	}
	
	// 전체 트리를 화면에 출력한다
	this.display = function() {
		//this.drawObj = function(depth, bLastNode, decorator, paintingDiv) {
		this.entryMap.getValue(0).drawObj(0,false,"");
		this.paintingDiv.innerHTML = this.treeHTML;
		//document.getElementById("tx").value=this.treeHTML;
	}

	this.writeTreeHtml = function(txt){
		//document.write(txt);
		this.treeHTML+=txt;
	}

	this.msDownOnFold = function(objId) {
		var clickedNode = this.entryMap.get(objId);
		if(clickedNode.nChildren>0){
			if(!clickedNode.bOpen) this.clickOnNode(objId);
		}
	}

	this.clickOnItem = function() {
	}

	// 폴더에만 node가 존재하므로 폴더일때만 작동한다
	this.clickOnNode = function(objId) {

		var state       = false;
		var clickedNode = this.entryMap.get(objId);

		if(!clickedNode.drawedSub){
			document.body.style.cursor = "wait";
			this.paintingDiv = document.getElementById("sub"+objId);
			
			this.treeHTML = this.paintingDiv.innerHTML;
			clickedNode.drawSubFolder();
			this.paintingDiv.innerHTML = this.treeHTML;
			document.body.style.cursor = "default";
		}
		
		state       = clickedNode.bOpen;
		if(objId!=topFoldId){
			if(state){
				document.getElementById("sub"+objId).style.display = "none";
				document.getElementById("fIc"+objId).src = this.images.folderclosed;
				if(clickedNode.bLastNode) document.getElementById("nIc"+objId).src = this.images.plastnode;
				else document.getElementById("nIc"+objId).src = this.images.pnode;
			} else {
				document.getElementById("sub"+objId).style.display = "";
				document.getElementById("fIc"+objId).src = this.images.folderopen;
				if(clickedNode.bLastNode) document.getElementById("nIc"+objId).src = this.images.mlastnode;
				else document.getElementById("nIc"+objId).src = this.images.mnode;
			}
			clickedNode.bOpen = !state;
		}
	}

}

// composite패턴에서 최상위의 component의 역할을 하는 객체
// 이 객체는 단독으로 사용될 수 없고 항상 Folder또는 Item객체의 멤버로 등록되어야 한다.
// 왜냐하면 자바스크립트는 상속의 개념이 없기 때문에 Entry객체를 Folder 또는 Item객체의
// 상위 객체로서 사용한다
function Entry(treeObj, id, description, value)
{
  // 엔트가 등록되는 트리객체
  this.treeObj = treeObj;
	
	this.isfolder = true;

  // 객체설명
	this.desc    = description;	
	// 코드값
  this.value   = value;
	// 객체식별자(인덱스테이블에서 식별자로서 역할을 함)
	this.id	     = id;
	// HTML중 객체의 테이블 식별자
	this.obj     = null;
	// HTML중 객체의 이미지식별자
	this.iconImg = null;
	// HTML중 각 노드의 이미지식별자
	this.nodeImg = null;
	
	// 이 함수는 가상함수를 제공하는 것으로 기본적으로 자신의 식별자를 만든다
	// 매개변수 obj를 사용하기 위해서 재정의하여 사용해야 한다
	this.add = function(obj){
		this.id = obj.id;
		this.treeObj.setObject(this.id, obj);
		this.treeObj.count++;
	}
}

function Folder(treeObj, id, folderDesc, value){

	// Entry객체를 상속한다.
	this.entry     = new Entry(treeObj, id, folderDesc, value);

	this.id = id;
	
	// 자식객체를 저장하는 배열
	this.fChildren  = new Array();	//하위폴더
	this.iChildren  = new Array();	//하위아이템
	
	this.nChildren = 0;
	this.nFChildren = 0;
	this.nIChildren = 0;

	// 현재 폴더가 마지막 노드인지를 판단하는 플래그
	this.bLastNode = false;
	// 폴더가 열려있는지를 판단하는 플래그
	this.bOpen     = true;

	//자신의 하위 폴더를 그렸는지 여부
	this.drawedSub = false;
	this.decorator = "";
	this.depth = 0;

	
	// 객체를 초기화한다
	this.init = function() {
		// 인덱스 테이블에 등록한다
		this.bOpen = this.entry.treeObj.openAll;
		this.entry.add(this);
	}

	this.addFolder  = function(obj) {
		// 하위폴더 등록
		this.fChildren[this.nFChildren] = obj;
		this.nChildren++;
		this.nFChildren++;
		return obj;
	}

	this.addItem  = function(obj) {
		// 하위폴더 등록
		this.iChildren[this.nIChildren] = obj;
		this.nChildren++;
		this.nIChildren++;
		return obj;
	}


	// 객체의 내용에 장식을 붙여 HTML형식으로 출력한다
	this.drawObj = function(depth, bLastNode, decorator) {
		this.depth = depth;
		this.bLastNode = bLastNode;
		this.decorator = decorator;
		var folderId   = this.entry.id;
		var subDivId   = "sub" + this.entry.id;
		var folderIcon = "fIc" + this.entry.id;
		var nodeIcon = "nIc" + this.entry.id;
		var textId = "t" + this.entry.id;
		var treeName = this.entry.treeObj.name;
		var spanStyle = "style='cursor:pointer'";
		var nodeImgSrc = "";
		this.entry.treeObj.writeTreeHtml("<div id='"+folderId+"' class='tree'>");

		this.entry.treeObj.writeTreeHtml(this.decorator);

		//node 그리기
		if(this.depth > 0){

			//하위 폴더나 아이템이 있는 경우
			if ( this.nChildren > 0 ) {
				if ( bLastNode ){
					if(this.bOpen) nodeImgSrc = this.entry.treeObj.images.mlastnode;
					else nodeImgSrc = this.entry.treeObj.images.plastnode;
					this.decorator += "<img id='"+nodeIcon+"' src='"+this.entry.treeObj.images.blank+"' align='absmiddle'>";
				}else{
					if(this.bOpen) nodeImgSrc = this.entry.treeObj.images.mnode;
					else nodeImgSrc = this.entry.treeObj.images.pnode;
					this.decorator += "<img id='"+nodeIcon+"' src='"+this.entry.treeObj.images.vline+"' align='absmiddle'>";
				}

				this.entry.treeObj.writeTreeHtml("<img id='"+nodeIcon+"' src='"+nodeImgSrc+"' onClick='"+treeName+".clickOnNode(\""+this.entry.id+"\");' align='absmiddle'>");
			}else{
				if ( bLastNode ) nodeImgSrc = this.entry.treeObj.images.lastnode;
				else nodeImgSrc = this.entry.treeObj.images.node;
				this.entry.treeObj.writeTreeHtml("<img id='"+nodeIcon+"' src='"+nodeImgSrc+"' align='absmiddle'>");
			}
		}
		
		//폴더 그리기
		if ( this.nChildren > 0 && this.bOpen ) {
			this.entry.treeObj.writeTreeHtml("<img id='"+folderIcon+"' src='"+this.entry.treeObj.images.folderopen+"' align='absmiddle'>");
		} else {
			this.entry.treeObj.writeTreeHtml("<img id='"+folderIcon+"' src='"+this.entry.treeObj.images.folderclosed+"' align='absmiddle'>");
		}

		//폴더명 그리기
		this.entry.treeObj.writeTreeHtml("<span id="+textId+"  onMouseDown="+treeName+".msDownOnFold('"+this.entry.id+"');   onClick="+treeName+".clickOnItem('"+this.entry.id+"'); "+spanStyle+">"+this.entry.desc+"</span>");

		this.entry.treeObj.writeTreeHtml("</div>\n");

		//하위폴더 DIV 시작
		if(this.depth > 0){
			if(this.bOpen) this.entry.treeObj.writeTreeHtml("\n<div id='"+subDivId+"'>");
			else this.entry.treeObj.writeTreeHtml("\n<div id='"+subDivId+"' style='display:none'>");
		}

		//하위 아이템 그리기
		for (var i=0;i<this.nIChildren;i++){
			if ( i == (this.nIChildren-1) && this.nFChildren==0)
				this.iChildren[i].drawObj(true, this.decorator);
			else
				this.iChildren[i].drawObj(false, this.decorator);
		}

		//하위 폴더 그리기
		if (this.depth == 0 || this.bOpen){
			this.drawedSub = true;
			for (var i=0;i<this.nFChildren;i++){
				if ( i == (this.nFChildren-1))
					this.fChildren[i].drawObj(this.depth+1, true, this.decorator);
				else
					this.fChildren[i].drawObj(this.depth+1, false, this.decorator);
			}
		}

		
		//하위폴더 DIV 끝
		if(this.depth > 0){
			this.entry.treeObj.writeTreeHtml("</div>\n\n");
		}
	}

	// 객체의 내용에 장식을 붙여 HTML형식으로 출력한다
	this.drawSubFolder = function() {
		this.drawedSub = true;
		for (var i=0;i<this.nFChildren;i++){
			if ( i == (this.nFChildren-1))
				this.fChildren[i].drawObj(this.depth+1, true, this.decorator);
			else
				this.fChildren[i].drawObj(this.depth+1, false, this.decorator);
		}
	}
	
	//선택한 폴더의 하위 폴더를 배열에 담는다
	this.setArrSubDir = function(arrObj){
		for (var i=0;i<this.nFChildren;i++){
			this.fChildren[i].setArrSubDir(arrObj);
		}
		arrObj.push(this);
	}

	// 생성자의 역할을 수행한다(변수의 초기화)
	this.init();
}


function Item(treeObj, id, itemDesc, value){

	// Entry객체를 상속한다.
	this.entry     = new Entry(treeObj, id, itemDesc, value);

	this.id = id;
	
	// 객체를 초기화한다
	this.init = function() {
		// 인덱스 테이블에 등록한다
		this.entry.add(this);
		this.entry.isfolder = false;
	}

	// 객체의 내용에 장식을 붙여 HTML형식으로 출력한다
	this.drawObj = function(bLastNode, decorator) {
		this.bLastNode = bLastNode;
		var iconId   = this.entry.id;
		var itemIcon = "ic" + this.entry.id;
		var nodeIcon = "nIc" + this.entry.id;
		var textId = "t" + this.entry.id;
		var treeName = this.entry.treeObj.name;
		var spanStyle = "style='cursor:pointer'";
		var nodeImgSrc = "";
		this.entry.treeObj.writeTreeHtml("<NOBR><div id='"+iconId+"' class='tree'>");
		this.entry.treeObj.writeTreeHtml(decorator);

		//node 그리기
		if ( bLastNode ) nodeImgSrc = this.entry.treeObj.images.lastnode;
		else nodeImgSrc = this.entry.treeObj.images.node;
		this.entry.treeObj.writeTreeHtml("<img id='"+nodeIcon+"' src='"+nodeImgSrc+"' align='absmiddle'>");
		
		//아이템 그리기
		this.entry.treeObj.writeTreeHtml("<img id='"+itemIcon+"' src='"+this.entry.treeObj.images.doc+"' align='absmiddle'>");

		//아이템명 그리기
		this.entry.treeObj.writeTreeHtml("<span id="+textId+" onClick="+treeName+".clickOnItem('"+this.entry.id+"'); "+spanStyle+">"+this.entry.desc+"</span>");

		this.entry.treeObj.writeTreeHtml("</div></NOBR>");
	}

	// 생성자의 역할을 수행한다(변수의 초기화)
	this.init();
}



////////

function HashMap()
{
   this.keys = new Array();
   this.values = new Array();
   /**
    * Removes all mappings from this map.
    */
   this.clear = function()
   {
   }
   /**
    * Returns true if this map contains a mapping for the specified key.
    */
   this.containsKey = function(key)
   {
   }
   /**
    * Returns true if this map maps one or more keys to the specified value.
    */
   this.containsValue = function(value)
   {
   }
   /**
    * Returns true if this map contains no key-value mappings.
    */
   this.isEmpty = function()
   {
   }
   /**
    * Associates the specified value with the specified key in this map.
    */
   this.put = function(key, value)
   {
      this.keys[this.keys.length] = key;
      this.values[this.values.length] = value;
   }
   /**
    * Returns the value to which the specified key is mapped in this identity hash map, or null if the map contains no mapping for this key
    */
   this.get = function(key)
   {
      for(var i = 0; i < this.keys.length; i++)
      {
         if(this.keys[i] == key) return this.values[i];
      }
      return null;
   }

   this.getValue = function(idx)
   {
      return this.values[idx];
   }

   /**
    * Returns the number of key-value mappings in this map.
    */
   this.size = function()
   {
   }
}


function TreeImages(imgpath)
{
	this.plastnode    = imgpath + "/plastnode.gif";
	this.folderopen   = imgpath + "/folderopen.gif";
	this.mnode        = imgpath + "/mnode.gif";
	this.pnode        = imgpath + "/pnode.gif";
	this.blank        = imgpath + "/blank.gif";
	this.folderclosed = imgpath + "/folderclosed.gif";
	this.mlastnode    = imgpath + "/mlastnode.gif";
	this.lastnode     = imgpath + "/lastnode.gif";
	this.node         = imgpath + "/node.gif";
	this.doc          = imgpath + "/doc.gif";
	this.vline        = imgpath + "/vline.gif";
	
	this.init = function() {
		// 이미지를 preloading한다
		var img1 = new Image();
		img1.src = this.plastnode;
		
		var img2 = new Image();
		img2.src = this.folderopen;
		
		var img3 = new Image();
		img3.src = this.mnode;
		
		var img4 = new Image();
		img4.src = this.pnode;
		
		var img5 = new Image();
		img5.src = this.blank;
		
		var img6 = new Image();
		img6.src = this.folderclosed;
		
		var img7 = new Image();
		img7.src = this.mlastnode;
		
		var img8 = new Image();
		img8.src = this.lastnode;
		
		var img9 = new Image();
		img9.src = this.node;
		
		var img10 = new Image();
		img10.src = this.doc;
		
		var img11 = new Image();		
		img11.src = this.vline;
	}
	
	this.init();
}