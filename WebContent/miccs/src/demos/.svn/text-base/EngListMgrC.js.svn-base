
demos.EngListMgrC = new Ext.form.FormPanel({
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
