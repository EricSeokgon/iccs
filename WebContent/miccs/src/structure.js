sink.Structure = [
    {
        text: '사용전검사',
        cls: 'launchscreen',
        items: [
            {
                text: '사용전검사대상목록',
                card: demos.UseBeforeMgr,
                source: 'src/demos/UseBeforeMgr.js',
                leaf: true
            },
            {
                text: '사용전검사완료목록',
                card: demos.UseBeforeMgrRes,
                source: 'src/demos/UseBeforeMgrRes.js',
                leaf: true
            }
        ]
    },
    {
        text: '정보통신공사업',
        cls: 'launchscreen',
        card: demos.RegListMgr,
        source: 'src/demos/RegListMgr.js',
        items: [

            {
                text: '정보통신공사업자검색',
                card: demos.RegListMgr,
                source: 'src/demos/RegListMgr.js',
                items: [
                    {
                        text: '공사업 등록정보',
                        card: demos.RegListMgrC,
                        source: 'src/demos/RegListMgrC.js',
                        leaf: true
                    }
                ]
            },
            {
                text: '기술자조회',
                card: demos.EngListMgr,
                source: 'src/demos/EngListMgr.js',
                items: [
                    {
                        text: '기술자 등록정보',
                        card: demos.RegListMgrC,
                        source: 'src/demos/RegListMgrC.js',
                        leaf: true
                    }
                ]
            }
        ]
    },
    {
        text: '위치 검색',
        card: demos.Map,
        source: 'src/demos/map.js',
        leaf: true
        
    }
];



Ext.regModel('Demo', {
    fields: [
        {name: 'text',        type: 'string'},
        {name: 'source',      type: 'string'},
        {name: 'preventHide', type: 'boolean'},
        {name: 'cardSwitchAnimation'},
        {name: 'card'}
    ]
});

sink.StructureStore = new Ext.data.TreeStore({
    model: 'Demo',
    root: {
        items: sink.Structure
    },
    proxy: {
        type: 'ajax',
        reader: {
            type: 'tree',
            root: 'items'
        }
    }
});
