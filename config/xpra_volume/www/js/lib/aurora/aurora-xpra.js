(function(){var __hasProp={}.hasOwnProperty;function XpraSource(){}AV.XpraSource=(function(child,parent){for(var key in parent)__hasProp.call(parent,key)&&(child[key]=parent[key]);function ctor(){this.constructor=child}ctor.prototype=parent.prototype,child.prototype=new ctor,child.__super__=parent.prototype}(XpraSource,AV.EventEmitter),XpraSource.prototype.start=function(){return!0},XpraSource.prototype.pause=function(){return!0},XpraSource.prototype.reset=function(){return!0},XpraSource.prototype._on_data=function(data){var buf=new AV.Buffer(data);return this.emit("data",buf)},XpraSource),AV.Asset.fromXpraSource=function(){var source;return source=new AV.XpraSource,new AV.Asset(source)},AV.Player.fromXpraSource=function(){var asset;return asset=AV.Asset.fromXpraSource(),new AV.Player(asset)}}).call(this);