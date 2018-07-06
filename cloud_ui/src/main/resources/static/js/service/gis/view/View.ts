declare let require : any;


namespace com.cloudo.gis {

    //引入图层接口
    import BaseLayer = com.cloudo.gis.layer.BaseLayer;

    export class View {
        //视图类型：3D视图标识
        public static VIEW_3D : string = "3d";
        //视图类型：2D视图标识
        public static VIEW_2D : string = "2d";


        //是否是混合地图（同时包含2d， 3d）
        private mixed : boolean = false;
        //视图所在element
        private viewElement : string;
        //视图容器
        private viewContainer : any;
        //地图对象
        private map : any;
        //地图边界
        private extent : any;
        //默认坐标系
        private spatialReference : number = 4326;

        //视图创建完成执行函数
        private _onViewCreateEvent : Function;
        //所有视图创建完成后执行函数
        private _onAllViewCreateEvent : Function;


        //视图添加的所有图层
        private allLayers : Array<BaseLayer> = new Array<BaseLayer>();
        //以map形式保存所有图层，方便获取
        private allLayersMap : {[index:string]: BaseLayer} = {};

        /**
         * 构造函数
         * @param {string} viewElement 视图所在dom
         * @param {boolean} mixed 可选 是否是混合地图，默认boolean
         * @param {number} spatialReference 可选，坐标系，默认4326
         */
        constructor(viewElement : string, mixed ? : boolean, spatialReference ? : number) {
            this.viewElement = viewElement;
            if(mixed) {
                this.mixed = mixed;
            }
            if(spatialReference) {
                this.spatialReference = spatialReference;
            }

            //初始化视图容器
            this.viewContainer = {
                mapView: null,
                sceneView: null,
                activeView: null,
                container: this.viewElement
            }

            //初始化边界
            //TODO 测试代码
            this.extent = {
                xmin: 121.926742,
                ymin: 37.3492609,
                xmax: 122.189069,
                ymax: 37.503023,
                spatialReference: this.spatialReference
            }
        }


        /**
         * 创建视图
         * @param {String} viewType 创建的视图类型3D/2d，with3D为true该参数无效，如果未设置默认2d
         */
        public createView(viewType ? : String) {
            let viewObject : View = this;
            if (!viewType) {
                viewType = View.VIEW_2D;
            }
            //创建地图
            require([
                "esri/Map"
            ], function (Map) {
                viewObject.map = new Map({
                    // basemap: "satellite",
                    basemap: "gray-vector",
                    ground: "world-elevation"
                });

                //创建视图
                viewObject.defaultCreateView(viewType);
            });
        }

        /**
         * 所有gis视图创建完毕执行函数
         * @param {Function} onAllViewCreateFunction 所有gis视图创建完成后回调
         * @returns {this} 当前对象
         */
        public onAllViewCreate(onAllViewCreateFunction : Function) {
            this._onAllViewCreateEvent = onAllViewCreateFunction;
            return this;
        }

        /**
         * 获取当前的gis视图容器
         * @returns {any} gis视图容器
         */
        public getViewContainer() {
            return this.viewContainer;
        }




        //--------------- 私有方法 -------------
        /**
         * 创建视图
         * @param {String} viewType 创建的视图类型3D/2d，with3D为true该参数无效
         * @private
         */
        private defaultCreateView(viewType : String) {
            //视图初始化参数
            let initialViewParams = {
                map: this.map,
                extent : this.extent,
                container: this.viewContainer.container,
                constraints: {
                    rotationEnabled: false
                },
                viewType : viewType
            };

            if (this.mixed) {
                //创建2D 3D视图
                this.create2D3DViwe(initialViewParams);
            } else {
                this.createOneView(initialViewParams);
            }
        }


        /**
         * 创建单一视图
         * @param initialViewParams 创建视图参数
         */
        private createOneView(initialViewParams) {
            let viewObject : View = this;
            require([
                "esri/views/MapView",
                "esri/views/SceneView"
            ], function (MapView, SceneView) {
                //获取创建的视图类型
                let viewType = initialViewParams.viewType;
                let view : any;
                if (viewType == View.VIEW_3D) {
                    view = new SceneView(initialViewParams);
                    viewObject.viewContainer.sceneView = view;
                } else {
                    view = new MapView(initialViewParams);
                    viewObject.viewContainer.mapView = view;
                }
                //设置当前活动视图
                viewObject.viewContainer.activeView = view;

                //执行一个视图创建完成后回调
                // viewObject._onViewCreateEvent(view, viewObject);
                // //所有视图创建完成回调
                viewObject._afterAllViewCreate();
            });
        }

        /**
         * 创建3d，2d可切换视图
         * @param initialViewParams创建视图参数
         */
        private create2D3DViwe(initialViewParams) {
            let viewObject : View = this;
            require([
                "esri/views/MapView",
                "esri/views/SceneView"
            ], function (MapView, SceneView) {
                viewObject.viewContainer.mapView = createView(initialViewParams, View.VIEW_2D);
                viewObject.viewContainer.activeView = viewObject.viewContainer.mapView;

                initialViewParams.container = null;
                viewObject.viewContainer.sceneView = createView(initialViewParams, View.VIEW_3D);

                //所有视图创建完成回调
                viewObject._afterAllViewCreate();

                //创建视图
                function createView(params, type) {
                    var view;
                    var is2D = type === View.VIEW_2D;
                    if (is2D) {
                        view = new MapView(params);
                    } else {
                        view = new SceneView(params);
                    }

                    //调用视图创建完成回调事件
                    // viewObject._onViewCreateEvent(view);
                    return view;
                }
            });
        }

        /**
         * 所有gis视图初始化完成后执行
         * 1、执行回调函数
         * 2、初始化添加的所有图层
         * @private
         */
        private _afterAllViewCreate() {
            //执行回调
            if (this._onAllViewCreateEvent) {
                this._onAllViewCreateEvent(this);
            }

            // //加载图层
            // for(let layer of this.allLayers) {
            //     layer.init(this.viewContainer);
            // }

        }
    }
}















