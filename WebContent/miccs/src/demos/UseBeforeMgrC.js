   demos.UseBeforeMgrC = new Ext.form.FormPanel({
            fullscreen:true,        
            scroll: 'vertical',
            standardSubmit : false,
            items: [{
                    xtype: 'fieldset',
                    defaults: {
            	        labelAlign: 'left',
                        labelWidth: '150'
                    },
                    items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        label: '지역구분',
                        useClearIcon: true,
                        autoCapitalize : true
                    }, {
                        xtype: 'textfield',
                        name : 'birthday',
                        label: '현장주소',
                        useClearIcon: false
                    },{
                        xtype: 'textfield',
                        name : 'cool',
                        label: '건축주',
                        
                    }, 
                    {
                        layout: {
                            type: 'hbox',
                            align: 'strech',
                            labelAlign: 'center'
                        
                        }, 
                    items: [
                       {
                        xtype: 'textfield',
                        name : 'spinner',
                        label: '시공업체'
                      },
                       {
                        xtype: 'datepickerfield',
                        name : 'start_date',
                        label: '접수일자'
                        
                    }, {
                        xtype: 'datepickerfield',
                        name : 'end_date',
                        label: '~'
                        
                    }, {
                        xtype: 'button',
                        name: 'number',
                        width:100,
                        bgcolor:'red',
                        align:'right',
                        text: '검색'
                    }]
                    }]
            }]
        });
