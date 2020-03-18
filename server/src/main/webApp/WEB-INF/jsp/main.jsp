<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <script src="//unpkg.com/vue/dist/vue.js"></script>
    <script src="//unpkg.com/element-ui@2.13.0/lib/index.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <link rel="stylesheet" href="//unpkg.com/element-ui@2.13.0/lib/theme-chalk/index.css" type="text/css"/>

    <div id="app">
        <template>
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                <el-tab-pane label="商品" name="first">商品</el-tab-pane>
                <el-tab-pane label="我的订单" name="second">我的订单</el-tab-pane>
                <el-tab-pane label="购物车" name="third">购物车</el-tab-pane>
                <el-tab-pane label="关于" name="fourth">关于</el-tab-pane>
            </el-tabs>
        </template>
    </div>
    <div id="app2">
        <template>
            <el-table
                    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                    style="width: 100%">
                <el-table-column
                        label="商品名称"
                        prop="name">
                </el-table-column>
                <el-table-column
                        label="价格"
                        prop="price">
                </el-table-column>
                <el-table-column
                        label="描述"
                        prop="description">
                </el-table-column>
                <el-table-column
                        label="剩余"
                        prop="number">
                </el-table-column>
                <el-table-column
                        align="right">
                    <template slot="header" slot-scope="scope">
                        <el-input
                                v-model="search"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                    </template>
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">加入购物车</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">直接购买</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </template>
    </div>

    <div id="app22" style="display: none">
        <template>
            <el-table
                    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                    style="width: 100%">
                <el-table-column
                        label="商品名称"
                        prop="name">
                </el-table-column>
                <el-table-column
                        label="单价"
                        prop="price">
                </el-table-column>
                <el-table-column
                        label="数量"
                        prop="number">
                </el-table-column>
                <el-table-column
                        label="描述"
                        prop="description">
                </el-table-column>
                <el-table-column
                        align="right">
                    <template slot="header" slot-scope="scope">
                        <el-input
                                v-model="search"
                                size="mini"
                                placeholder="输入关键字搜索"/>
                    </template>
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">结算</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </template>
    </div>

    <div id="app3">
        <el-button-group>
            <el-button type="primary" icon="el-icon-arrow-left">上一页</el-button>
            <el-button type="primary">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
        </el-button-group>
    </div>

    <script>
        var commodityData = ${commodityList};

        buildLayout();

        if(commodityData != null && commodityData.size > 0) {
            buildTable("app2",commodityData.list);
        }

        new Vue().$mount('#app3')
        
        function buildLayout() {
            var Main = {
                data() {
                    return {
                        activeName: 'first'
                    };
                },
                methods: {
                    handleClick(tab, event) {
                        ajaxCall("/order/myOrder","get",null);
                    }
                }
            };
            var Ctor = Vue.extend(Main)
            new Ctor().$mount('#app')
        }

        function buildTable(appId,data) {
            var Main = {
                data() {
                    return {
                        tableData: data,
                        search: ''
                    }
                },
                methods: {
                    handleEdit(index, row) {
                        var d = {};
                        d.commodityCode = row.code;
                        ajaxCall("/order/myOrder","post",d);
                    },
                    handleDelete(index, row) {
                        console.log(index, row);
                    }
                },
            }
            var Ctor = Vue.extend(Main);
            new Ctor().$mount('#'+ appId);
        }

        /* ajax请求： 加入购物车  */
        function ajaxCall(url,m,d) {
            $.ajax({
                url:url,
                type:m,
                data:d,
                success:function(msg){
                    if("get" == m){
                        var data = JSON.parse(msg);
                        data = dataFormat(data);
                        $("#app2").hide();
                        $("#app22").show();
                        buildTable("app22",data);
                    } else if("post" == m) {
                       alert("添加成功" + msg);
                    }
                }
            })
        }

        function dataFormat(data) {
            var d = [];
            for (var i = 0; i< data.length; i++){
                var d1 = data[i], d2 = {}, d3 = d1.commodity;
                d2.number = d1.number;
                d2.name = d3.name;
                d2.description = d3.description;
                d2.price = d3.price;
                d[i] = d2;
            }
            return d;
        }

    </script>
</body>
</html>
