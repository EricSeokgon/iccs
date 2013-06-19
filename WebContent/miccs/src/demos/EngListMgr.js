Ext.regModel('sido',{
	fields: [
	         {name: 'sidocode', type: 'string'},
	         {name: 'sidonm', type: 'string'}
	         ]
	        });
var sidoStore = new Ext.data.JsonStore({
	data: [
	       {sidocode: 'jwjw', sidonm: '강원'},
	       {sidocode: 'gggg', sidonm: '경기'},
	       {sidocode: 'gngn', sidonm: '경남'},
	       {sidocode: 'gjgj', sidonm: '광주'},
	       {sidocode: 'dgdg', sidonm: '대구'},
	       {sidocode: 'djdj', sidonm: '대전'},
	       {sidocode: 'bsbs', sidonm: '부산'},
	       {sidocode: 'susu', sidonm: '서울'},
	       {sidocode: 'usus', sidonm: '울산'},
	       {sidocode: 'icic', sidonm: '인천'},
	       {sidocode: 'jnjn', sidonm: '전남'},
	       {sidocode: 'jbjb', sidonm: '전북'},
	       {sidocode: 'jjjj', sidonm: '제주'},
	       {sidocode: 'cncn', sidonm: '충남'},
	       {sidocode: 'cbcb', sidonm: '충북'}
	              
	       ],
	       model:'sido',
	       autoLoad: true,
	       autoDestroy: true
});

Ext.regModel('regsch',{
	fields: [
	         {name: 'regcode', type: 'string'},
	         {name: 'schnm', type: 'string'}
	         ]
	        });
var schStore = new Ext.data.JsonStore({
	data: [
	       {regcode: '1', schnm: '등록번호'},
	       {regcode: '2', schnm: '상호'},
	       {regcode: '3', schnm: '대표자'}
	              
	       ],
	       model:'regsch',
	       autoLoad: true,
	       autoDestroy: true
});

demos.EngListMgr = new Ext.form.FormPanel({
            fullscreen:true,        
            scroll: 'vertical',
            standardSubmit : false,
            items: [{
                    xtype: 'fieldset',
                      
                    
                        layout: {
                            type: 'hbox',
                            align: 'strech',
                            labelAlign: 'center'
                        
                        }, 
                    items: [
                       {
                        xtype: 'selectfield',
                        name : 'sido',
                        width: '20%',
                        labelWidth: 100,
                        label: '조건',
                        valueField:'sidocode',
                        displayField: 'sidonm',
                        store: sidoStore
                      },
                       {
                          xtype: 'selectfield',
                          name : 'regsch',
                          width: '20%',
                          labelWidth: 100,
                          valueField:'regcode',
                          displayField: 'schnm',
                          store: schStore
                        
                    }, {
                        xtype: 'textfield',
                        placeHolder: '데이터입력',
                        name : 'end_date'
                        
                        
                    }, {
                        xtype: 'button',
                        name: 'number',
                        width:100,
                        bgcolor:'red',
                        align:'right',
                        text: '검색'
                    }]
                    
            }]
        });
