declare let $ : any;


let gis : Gis;

$(document).ready(function () {
    gis = new Gis();

});

namespace com.cloudo.gis.page{

    import RestUtil = com.cloudo.util.RestUtil;

    export class Gis{

        //视图信息
        private view : View;

        private meteoinfoLayer : any = null;

        constructor() {
            this.view = new View("mapDiv");
            this.view.onAllViewCreate(this.addLayerDemo);
            this.view.createView("2d");
        }

        /**
         * 等值线demo
         * //TODO 测试代码 健壮性未考虑
         */
        public meteoDemo() : void {
            RestUtil.postAjax("system/test/meteoTest", null, drawLayer, null);

            let gisObject = this;

            function drawLayer(data) {
                //获取所有shape
                let polygonShapes = data["polygonShapes"];
                let colorBreaks = data["colorBreaks"]; //获取颜色

                require([
                    "esri/symbols/SimpleFillSymbol",
                    "esri/geometry/Polygon",
                    "esri/Graphic"
                ], function (SimpleFillSymbol, Polygon, Graphic) {
                    for(var i=0; i<polygonShapes.length; i++) {
                        var shape = polygonShapes[i];
                        var legendIndex = shape.legendIndex;

                        //获取颜色
                        var colorBreak = colorBreaks[legendIndex];
                        var color = colorBreak.color;
                        var fillSymbol = new SimpleFillSymbol({
                            color: [color.red, color.green, color.blue, 0.5],
                            outline: { // autocasts as new SimpleLineSymbol()
                                color: [255, 255, 255, 0],
                                width: 1
                            }
                        });

                        //获取shape内的多边形
                        var polygons = shape.polygons;
                        //将多边形画到地图上
                        for(var j=0; j<polygons.length; j++) {
                            var polygon = polygons[j];
                            //描画多边形

                            var polygon = new Polygon({
                                rings: convertRings(polygon.rings),
                                spatialReference : 4326
                            });



                            // Add the geometry and symbol to a new graphic
                            var polygonGraphic = new Graphic({
                                geometry: polygon,
                                symbol: fillSymbol
                            });
                            polygonGraphic.symbol = fillSymbol;

                            gisObject.meteoinfoLayer.add(polygonGraphic);

                        }
                    }
                });
            }

            function convertRings(rings) {
                var dealData = new Array();
                for(var i=0; i<rings.length; i++) {
                    var ring = rings[i];
                    var ringArray = new Array();
                    for(var j =0; j<ring.length; j++) {
                        var pointArray = new Array();
                        var point = ring[j];
                        pointArray.push(point.X, point.Y);
                        ringArray.push(pointArray);
                    }
                    dealData.push(ringArray);
                }

                return dealData;
            }
        }

        //TODO 测试代码 健壮性未考虑
        public addLayerDemo = (view : View) => {
            let gisObject = this;
            require([
                "esri/layers/GraphicsLayer"
            ], function (GraphicsLayer) {
                //创建等值面图层
                gisObject.meteoinfoLayer = new GraphicsLayer({
                    id : "meteoinfoLayer"
                });
                //将图层添加到地图
                view.getViewContainer().activeView.map.add(gisObject.meteoinfoLayer);
            });
        }

    }
}


import Gis = com.cloudo.gis.page.Gis;