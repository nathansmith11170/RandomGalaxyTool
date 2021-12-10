!function(e){function t(t){for(var o,r,i=t[0],a=t[1],s=0,c=[];s<i.length;s++)r=i[s],Object.prototype.hasOwnProperty.call(n,r)&&n[r]&&c.push(n[r][0]),n[r]=0;for(o in a)Object.prototype.hasOwnProperty.call(a,o)&&(e[o]=a[o]);for(l&&l(t);c.length;)c.shift()()}var o={},n={0:0};function r(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,r),n.l=!0,n.exports}r.e=function(e){var t=[],o=n[e];if(0!==o)if(o)t.push(o[2]);else{var i=new Promise((function(t,r){o=n[e]=[t,r]}));t.push(o[2]=i);var a,s=document.createElement("script");s.charset="utf-8",s.timeout=120,r.nc&&s.setAttribute("nonce",r.nc),s.src=function(e){return r.p+"VAADIN/build/vaadin-"+({}[e]||e)+"-"+{1:"f1663db2950a4fafe118",2:"e295e889214eed8c15d4",3:"0ec999508360825065b8",4:"740727ede4b71c616d54",5:"04f782a2852e7b5ef58d",6:"98ca007742636377057a"}[e]+".cache.js"}(e);var l=new Error;a=function(t){s.onerror=s.onload=null,clearTimeout(c);var o=n[e];if(0!==o){if(o){var r=t&&("load"===t.type?"missing":t.type),i=t&&t.target&&t.target.src;l.message="Loading chunk "+e+" failed.\n("+r+": "+i+")",l.name="ChunkLoadError",l.type=r,l.request=i,o[1](l)}n[e]=void 0}};var c=setTimeout((function(){a({type:"timeout",target:s})}),12e4);s.onerror=s.onload=a,document.head.appendChild(s)}return Promise.all(t)},r.m=e,r.c=o,r.d=function(e,t,o){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:o})},r.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var o=Object.create(null);if(r.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var n in e)r.d(o,n,function(t){return e[t]}.bind(null,n));return o},r.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="",r.oe=function(e){throw console.error(e),e};var i=window.webpackJsonp=window.webpackJsonp||[],a=i.push.bind(i);i.push=t,i=i.slice();for(var s=0;s<i.length;s++)t(i[s]);var l=a;r(r.s=30)}([function(e,t,o){"use strict";o.d(t,"f",(function(){return n})),o.d(t,"g",(function(){return r})),o.d(t,"b",(function(){return a})),o.d(t,"a",(function(){return s})),o.d(t,"d",(function(){return c})),o.d(t,"c",(function(){return d})),o.d(t,"e",(function(){return u}));
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const n=`{{lit-${String(Math.random()).slice(2)}}}`,r=`\x3c!--${n}--\x3e`,i=new RegExp(`${n}|${r}`),a="$lit$";class s{constructor(e,t){this.parts=[],this.element=t;const o=[],r=[],s=document.createTreeWalker(t.content,133,null,!1);let c=0,m=-1,p=0;const{strings:h,values:{length:g}}=e;for(;p<g;){const e=s.nextNode();if(null!==e){if(m++,1===e.nodeType){if(e.hasAttributes()){const t=e.attributes,{length:o}=t;let n=0;for(let e=0;e<o;e++)l(t[e].name,a)&&n++;for(;n-- >0;){const t=h[p],o=u.exec(t)[2],n=o.toLowerCase()+a,r=e.getAttribute(n);e.removeAttribute(n);const s=r.split(i);this.parts.push({type:"attribute",index:m,name:o,strings:s}),p+=s.length-1}}"TEMPLATE"===e.tagName&&(r.push(e),s.currentNode=e.content)}else if(3===e.nodeType){const t=e.data;if(t.indexOf(n)>=0){const n=e.parentNode,r=t.split(i),s=r.length-1;for(let t=0;t<s;t++){let o,i=r[t];if(""===i)o=d();else{const e=u.exec(i);null!==e&&l(e[2],a)&&(i=i.slice(0,e.index)+e[1]+e[2].slice(0,-a.length)+e[3]),o=document.createTextNode(i)}n.insertBefore(o,e),this.parts.push({type:"node",index:++m})}""===r[s]?(n.insertBefore(d(),e),o.push(e)):e.data=r[s],p+=s}}else if(8===e.nodeType)if(e.data===n){const t=e.parentNode;null!==e.previousSibling&&m!==c||(m++,t.insertBefore(d(),e)),c=m,this.parts.push({type:"node",index:m}),null===e.nextSibling?e.data="":(o.push(e),m--),p++}else{let t=-1;for(;-1!==(t=e.data.indexOf(n,t+1));)this.parts.push({type:"node",index:-1}),p++}}else s.currentNode=r.pop()}for(const e of o)e.parentNode.removeChild(e)}}const l=(e,t)=>{const o=e.length-t.length;return o>=0&&e.slice(o)===t},c=e=>-1!==e.index,d=()=>document.createComment(""),u=/([ \x09\x0a\x0c\x0d])([^\0-\x1F\x7F-\x9F "'>=/]+)([ \x09\x0a\x0c\x0d]*=[ \x09\x0a\x0c\x0d]*(?:[^ \x09\x0a\x0c\x0d"'`<>=]*|"[^"]*|'[^']*))$/},function(e,t,o){"use strict";o.d(t,"e",(function(){return S})),o.d(t,"d",(function(){return u.d})),o.d(t,"a",(function(){return k})),o.d(t,"c",(function(){return O})),o.d(t,"b",(function(){return P}));var n=o(5),r=o(0);function i(e,t){const{element:{content:o},parts:n}=e,r=document.createTreeWalker(o,133,null,!1);let i=s(n),a=n[i],l=-1,c=0;const d=[];let u=null;for(;r.nextNode();){l++;const e=r.currentNode;for(e.previousSibling===u&&(u=null),t.has(e)&&(d.push(e),null===u&&(u=e)),null!==u&&c++;void 0!==a&&a.index===l;)a.index=null!==u?-1:a.index-c,i=s(n,i),a=n[i]}d.forEach(e=>e.parentNode.removeChild(e))}const a=e=>{let t=11===e.nodeType?0:1;const o=document.createTreeWalker(e,133,null,!1);for(;o.nextNode();)t++;return t},s=(e,t=-1)=>{for(let o=t+1;o<e.length;o++){const t=e[o];if(Object(r.d)(t))return o}return-1};var l=o(9),c=o(8),d=o(13),u=o(6);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const m=(e,t)=>`${e}--${t}`;let p=!0;void 0===window.ShadyCSS?p=!1:void 0===window.ShadyCSS.prepareTemplateDom&&(console.warn("Incompatible ShadyCSS version detected. Please update to at least @webcomponents/webcomponentsjs@2.0.2 and @webcomponents/shadycss@1.3.1."),p=!1);const h=e=>t=>{const o=m(t.type,e);let n=c.a.get(o);void 0===n&&(n={stringsArray:new WeakMap,keyString:new Map},c.a.set(o,n));let i=n.stringsArray.get(t.strings);if(void 0!==i)return i;const a=t.strings.join(r.f);if(i=n.keyString.get(a),void 0===i){const o=t.getTemplateElement();p&&window.ShadyCSS.prepareTemplateDom(o,e),i=new r.a(t,o),n.keyString.set(a,i)}return n.stringsArray.set(t.strings,i),i},g=["html","svg"],f=new Set,v=(e,t,o)=>{f.add(e);const n=o?o.element:document.createElement("template"),r=t.querySelectorAll("style"),{length:l}=r;if(0===l)return void window.ShadyCSS.prepareTemplateStyles(n,e);const d=document.createElement("style");for(let e=0;e<l;e++){const t=r[e];t.parentNode.removeChild(t),d.textContent+=t.textContent}(e=>{g.forEach(t=>{const o=c.a.get(m(t,e));void 0!==o&&o.keyString.forEach(e=>{const{element:{content:t}}=e,o=new Set;Array.from(t.querySelectorAll("style")).forEach(e=>{o.add(e)}),i(e,o)})})})(e);const u=n.content;o?function(e,t,o=null){const{element:{content:n},parts:r}=e;if(null==o)return void n.appendChild(t);const i=document.createTreeWalker(n,133,null,!1);let l=s(r),c=0,d=-1;for(;i.nextNode();){d++;for(i.currentNode===o&&(c=a(t),o.parentNode.insertBefore(t,o));-1!==l&&r[l].index===d;){if(c>0){for(;-1!==l;)r[l].index+=c,l=s(r,l);return}l=s(r,l)}}}(o,d,u.firstChild):u.insertBefore(d,u.firstChild),window.ShadyCSS.prepareTemplateStyles(n,e);const p=u.querySelector("style");if(window.ShadyCSS.nativeShadow&&null!==p)t.insertBefore(p.cloneNode(!0),t.firstChild);else if(o){u.insertBefore(d,u.firstChild);const e=new Set;e.add(d),i(o,e)}};window.JSCompiler_renameProperty=(e,t)=>e;const x={toAttribute(e,t){switch(t){case Boolean:return e?"":null;case Object:case Array:return null==e?e:JSON.stringify(e)}return e},fromAttribute(e,t){switch(t){case Boolean:return null!==e;case Number:return null===e?null:Number(e);case Object:case Array:return JSON.parse(e)}return e}},y=(e,t)=>t!==e&&(t==t||e==e),b={attribute:!0,type:String,converter:x,reflect:!1,hasChanged:y};class w extends HTMLElement{constructor(){super(),this.initialize()}static get observedAttributes(){this.finalize();const e=[];return this._classProperties.forEach((t,o)=>{const n=this._attributeNameForProperty(o,t);void 0!==n&&(this._attributeToPropertyMap.set(n,o),e.push(n))}),e}static _ensureClassProperties(){if(!this.hasOwnProperty(JSCompiler_renameProperty("_classProperties",this))){this._classProperties=new Map;const e=Object.getPrototypeOf(this)._classProperties;void 0!==e&&e.forEach((e,t)=>this._classProperties.set(t,e))}}static createProperty(e,t=b){if(this._ensureClassProperties(),this._classProperties.set(e,t),t.noAccessor||this.prototype.hasOwnProperty(e))return;const o="symbol"==typeof e?Symbol():"__"+e,n=this.getPropertyDescriptor(e,o,t);void 0!==n&&Object.defineProperty(this.prototype,e,n)}static getPropertyDescriptor(e,t,o){return{get(){return this[t]},set(n){const r=this[e];this[t]=n,this.requestUpdateInternal(e,r,o)},configurable:!0,enumerable:!0}}static getPropertyOptions(e){return this._classProperties&&this._classProperties.get(e)||b}static finalize(){const e=Object.getPrototypeOf(this);if(e.hasOwnProperty("finalized")||e.finalize(),this.finalized=!0,this._ensureClassProperties(),this._attributeToPropertyMap=new Map,this.hasOwnProperty(JSCompiler_renameProperty("properties",this))){const e=this.properties,t=[...Object.getOwnPropertyNames(e),..."function"==typeof Object.getOwnPropertySymbols?Object.getOwnPropertySymbols(e):[]];for(const o of t)this.createProperty(o,e[o])}}static _attributeNameForProperty(e,t){const o=t.attribute;return!1===o?void 0:"string"==typeof o?o:"string"==typeof e?e.toLowerCase():void 0}static _valueHasChanged(e,t,o=y){return o(e,t)}static _propertyValueFromAttribute(e,t){const o=t.type,n=t.converter||x,r="function"==typeof n?n:n.fromAttribute;return r?r(e,o):e}static _propertyValueToAttribute(e,t){if(void 0===t.reflect)return;const o=t.type,n=t.converter;return(n&&n.toAttribute||x.toAttribute)(e,o)}initialize(){this._updateState=0,this._updatePromise=new Promise(e=>this._enableUpdatingResolver=e),this._changedProperties=new Map,this._saveInstanceProperties(),this.requestUpdateInternal()}_saveInstanceProperties(){this.constructor._classProperties.forEach((e,t)=>{if(this.hasOwnProperty(t)){const e=this[t];delete this[t],this._instanceProperties||(this._instanceProperties=new Map),this._instanceProperties.set(t,e)}})}_applyInstanceProperties(){this._instanceProperties.forEach((e,t)=>this[t]=e),this._instanceProperties=void 0}connectedCallback(){this.enableUpdating()}enableUpdating(){void 0!==this._enableUpdatingResolver&&(this._enableUpdatingResolver(),this._enableUpdatingResolver=void 0)}disconnectedCallback(){}attributeChangedCallback(e,t,o){t!==o&&this._attributeToProperty(e,o)}_propertyToAttribute(e,t,o=b){const n=this.constructor,r=n._attributeNameForProperty(e,o);if(void 0!==r){const e=n._propertyValueToAttribute(t,o);if(void 0===e)return;this._updateState=8|this._updateState,null==e?this.removeAttribute(r):this.setAttribute(r,e),this._updateState=-9&this._updateState}}_attributeToProperty(e,t){if(8&this._updateState)return;const o=this.constructor,n=o._attributeToPropertyMap.get(e);if(void 0!==n){const e=o.getPropertyOptions(n);this._updateState=16|this._updateState,this[n]=o._propertyValueFromAttribute(t,e),this._updateState=-17&this._updateState}}requestUpdateInternal(e,t,o){let n=!0;if(void 0!==e){const r=this.constructor;o=o||r.getPropertyOptions(e),r._valueHasChanged(this[e],t,o.hasChanged)?(this._changedProperties.has(e)||this._changedProperties.set(e,t),!0!==o.reflect||16&this._updateState||(void 0===this._reflectingProperties&&(this._reflectingProperties=new Map),this._reflectingProperties.set(e,o))):n=!1}!this._hasRequestedUpdate&&n&&(this._updatePromise=this._enqueueUpdate())}requestUpdate(e,t){return this.requestUpdateInternal(e,t),this.updateComplete}async _enqueueUpdate(){this._updateState=4|this._updateState;try{await this._updatePromise}catch(e){}const e=this.performUpdate();return null!=e&&await e,!this._hasRequestedUpdate}get _hasRequestedUpdate(){return 4&this._updateState}get hasUpdated(){return 1&this._updateState}performUpdate(){if(!this._hasRequestedUpdate)return;this._instanceProperties&&this._applyInstanceProperties();let e=!1;const t=this._changedProperties;try{e=this.shouldUpdate(t),e?this.update(t):this._markUpdated()}catch(t){throw e=!1,this._markUpdated(),t}e&&(1&this._updateState||(this._updateState=1|this._updateState,this.firstUpdated(t)),this.updated(t))}_markUpdated(){this._changedProperties=new Map,this._updateState=-5&this._updateState}get updateComplete(){return this._getUpdateComplete()}_getUpdateComplete(){return this.getUpdateComplete()}getUpdateComplete(){return this._updatePromise}shouldUpdate(e){return!0}update(e){void 0!==this._reflectingProperties&&this._reflectingProperties.size>0&&(this._reflectingProperties.forEach((e,t)=>this._propertyToAttribute(t,this[t],e)),this._reflectingProperties=void 0),this._markUpdated()}updated(e){}firstUpdated(e){}}w.finalized=!0;
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const _=(e,t)=>"method"===t.kind&&t.descriptor&&!("value"in t.descriptor)?Object.assign(Object.assign({},t),{finisher(o){o.createProperty(t.key,e)}}):{kind:"field",key:Symbol(),placement:"own",descriptor:{},initializer(){"function"==typeof t.initializer&&(this[t.key]=t.initializer.call(this))},finisher(o){o.createProperty(t.key,e)}};function S(e){return(t,o)=>void 0!==o?((e,t,o)=>{t.constructor.createProperty(o,e)})(e,t,o):_(e,t)}const C=Element.prototype;C.msMatchesSelector||C.webkitMatchesSelector;
/**
@license
Copyright (c) 2019 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/
const E=window.ShadowRoot&&(void 0===window.ShadyCSS||window.ShadyCSS.nativeShadow)&&"adoptedStyleSheets"in Document.prototype&&"replace"in CSSStyleSheet.prototype,T=Symbol();class k{constructor(e,t){if(t!==T)throw new Error("CSSResult is not constructable. Use `unsafeCSS` or `css` instead.");this.cssText=e}get styleSheet(){return void 0===this._styleSheet&&(E?(this._styleSheet=new CSSStyleSheet,this._styleSheet.replaceSync(this.cssText)):this._styleSheet=null),this._styleSheet}toString(){return this.cssText}}const O=(e,...t)=>{const o=t.reduce((t,o,n)=>t+(e=>{if(e instanceof k)return e.cssText;if("number"==typeof e)return e;throw new Error(`Value passed to 'css' function must be a 'css' function result: ${e}. Use 'unsafeCSS' to pass non-literal values, but\n            take care to ensure page security.`)})(o)+e[n+1],e[0]);return new k(o,T)};
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
(window.litElementVersions||(window.litElementVersions=[])).push("2.5.1");const z={};class P extends w{static getStyles(){return this.styles}static _getUniqueStyles(){if(this.hasOwnProperty(JSCompiler_renameProperty("_styles",this)))return;const e=this.getStyles();if(Array.isArray(e)){const t=(e,o)=>e.reduceRight((e,o)=>Array.isArray(o)?t(o,e):(e.add(o),e),o),o=t(e,new Set),n=[];o.forEach(e=>n.unshift(e)),this._styles=n}else this._styles=void 0===e?[]:[e];this._styles=this._styles.map(e=>{if(e instanceof CSSStyleSheet&&!E){const t=Array.prototype.slice.call(e.cssRules).reduce((e,t)=>e+t.cssText,"");return new k(String(t),T)}return e})}initialize(){super.initialize(),this.constructor._getUniqueStyles(),this.renderRoot=this.createRenderRoot(),window.ShadowRoot&&this.renderRoot instanceof window.ShadowRoot&&this.adoptStyles()}createRenderRoot(){return this.attachShadow(this.constructor.shadowRootOptions)}adoptStyles(){const e=this.constructor._styles;0!==e.length&&(void 0===window.ShadyCSS||window.ShadyCSS.nativeShadow?E?this.renderRoot.adoptedStyleSheets=e.map(e=>e instanceof CSSStyleSheet?e:e.styleSheet):this._needsShimAdoptedStyleSheets=!0:window.ShadyCSS.ScopingShim.prepareAdoptedCssText(e.map(e=>e.cssText),this.localName))}connectedCallback(){super.connectedCallback(),this.hasUpdated&&void 0!==window.ShadyCSS&&window.ShadyCSS.styleElement(this)}update(e){const t=this.render();super.update(e),t!==z&&this.constructor.render(t,this.renderRoot,{scopeName:this.localName,eventContext:this}),this._needsShimAdoptedStyleSheets&&(this._needsShimAdoptedStyleSheets=!1,this.constructor._styles.forEach(e=>{const t=document.createElement("style");t.textContent=e.cssText,this.renderRoot.appendChild(t)}))}render(){return z}}P.finalized=!0,P.render=(e,t,o)=>{if(!o||"object"!=typeof o||!o.scopeName)throw new Error("The `scopeName` option is required.");const r=o.scopeName,i=l.a.has(t),a=p&&11===t.nodeType&&!!t.host,s=a&&!f.has(r),c=s?document.createDocumentFragment():t;if(Object(l.b)(e,c,Object.assign({templateFactory:h(r)},o)),s){const e=l.a.get(c);l.a.delete(c);const o=e.value instanceof d.a?e.value.template:void 0;v(r,c,o),Object(n.b)(t,t.firstChild),t.appendChild(c),l.a.set(t,e)}!i&&a&&window.ShadyCSS.styleElement(t.host)},P.shadowRootOptions={mode:"open"}},function(e,t,o){"use strict";o.d(t,"a",(function(){return u})),o.d(t,"b",(function(){return m})),o.d(t,"e",(function(){return p})),o.d(t,"c",(function(){return h})),o.d(t,"f",(function(){return g})),o.d(t,"g",(function(){return f})),o.d(t,"d",(function(){return x}));var n=o(10),r=o(5),i=o(4),a=o(13),s=o(11),l=o(0);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const c=e=>null===e||!("object"==typeof e||"function"==typeof e),d=e=>Array.isArray(e)||!(!e||!e[Symbol.iterator]);class u{constructor(e,t,o){this.dirty=!0,this.element=e,this.name=t,this.strings=o,this.parts=[];for(let e=0;e<o.length-1;e++)this.parts[e]=this._createPart()}_createPart(){return new m(this)}_getValue(){const e=this.strings,t=e.length-1,o=this.parts;if(1===t&&""===e[0]&&""===e[1]){const e=o[0].value;if("symbol"==typeof e)return String(e);if("string"==typeof e||!d(e))return e}let n="";for(let r=0;r<t;r++){n+=e[r];const t=o[r];if(void 0!==t){const e=t.value;if(c(e)||!d(e))n+="string"==typeof e?e:String(e);else for(const t of e)n+="string"==typeof t?t:String(t)}}return n+=e[t],n}commit(){this.dirty&&(this.dirty=!1,this.element.setAttribute(this.name,this._getValue()))}}class m{constructor(e){this.value=void 0,this.committer=e}setValue(e){e===i.a||c(e)&&e===this.value||(this.value=e,Object(n.b)(e)||(this.committer.dirty=!0))}commit(){for(;Object(n.b)(this.value);){const e=this.value;this.value=i.a,e(this)}this.value!==i.a&&this.committer.commit()}}class p{constructor(e){this.value=void 0,this.__pendingValue=void 0,this.options=e}appendInto(e){this.startNode=e.appendChild(Object(l.c)()),this.endNode=e.appendChild(Object(l.c)())}insertAfterNode(e){this.startNode=e,this.endNode=e.nextSibling}appendIntoPart(e){e.__insert(this.startNode=Object(l.c)()),e.__insert(this.endNode=Object(l.c)())}insertAfterPart(e){e.__insert(this.startNode=Object(l.c)()),this.endNode=e.endNode,e.endNode=this.startNode}setValue(e){this.__pendingValue=e}commit(){if(null===this.startNode.parentNode)return;for(;Object(n.b)(this.__pendingValue);){const e=this.__pendingValue;this.__pendingValue=i.a,e(this)}const e=this.__pendingValue;e!==i.a&&(c(e)?e!==this.value&&this.__commitText(e):e instanceof s.b?this.__commitTemplateResult(e):e instanceof Node?this.__commitNode(e):d(e)?this.__commitIterable(e):e===i.b?(this.value=i.b,this.clear()):this.__commitText(e))}__insert(e){this.endNode.parentNode.insertBefore(e,this.endNode)}__commitNode(e){this.value!==e&&(this.clear(),this.__insert(e),this.value=e)}__commitText(e){const t=this.startNode.nextSibling,o="string"==typeof(e=null==e?"":e)?e:String(e);t===this.endNode.previousSibling&&3===t.nodeType?t.data=o:this.__commitNode(document.createTextNode(o)),this.value=e}__commitTemplateResult(e){const t=this.options.templateFactory(e);if(this.value instanceof a.a&&this.value.template===t)this.value.update(e.values);else{const o=new a.a(t,e.processor,this.options),n=o._clone();o.update(e.values),this.__commitNode(n),this.value=o}}__commitIterable(e){Array.isArray(this.value)||(this.value=[],this.clear());const t=this.value;let o,n=0;for(const r of e)o=t[n],void 0===o&&(o=new p(this.options),t.push(o),0===n?o.appendIntoPart(this):o.insertAfterPart(t[n-1])),o.setValue(r),o.commit(),n++;n<t.length&&(t.length=n,this.clear(o&&o.endNode))}clear(e=this.startNode){Object(r.b)(this.startNode.parentNode,e.nextSibling,this.endNode)}}class h{constructor(e,t,o){if(this.value=void 0,this.__pendingValue=void 0,2!==o.length||""!==o[0]||""!==o[1])throw new Error("Boolean attributes can only contain a single expression");this.element=e,this.name=t,this.strings=o}setValue(e){this.__pendingValue=e}commit(){for(;Object(n.b)(this.__pendingValue);){const e=this.__pendingValue;this.__pendingValue=i.a,e(this)}if(this.__pendingValue===i.a)return;const e=!!this.__pendingValue;this.value!==e&&(e?this.element.setAttribute(this.name,""):this.element.removeAttribute(this.name),this.value=e),this.__pendingValue=i.a}}class g extends u{constructor(e,t,o){super(e,t,o),this.single=2===o.length&&""===o[0]&&""===o[1]}_createPart(){return new f(this)}_getValue(){return this.single?this.parts[0].value:super._getValue()}commit(){this.dirty&&(this.dirty=!1,this.element[this.name]=this._getValue())}}class f extends m{}let v=!1;(()=>{try{const e={get capture(){return v=!0,!1}};window.addEventListener("test",e,e),window.removeEventListener("test",e,e)}catch(e){}})();class x{constructor(e,t,o){this.value=void 0,this.__pendingValue=void 0,this.element=e,this.eventName=t,this.eventContext=o,this.__boundHandleEvent=e=>this.handleEvent(e)}setValue(e){this.__pendingValue=e}commit(){for(;Object(n.b)(this.__pendingValue);){const e=this.__pendingValue;this.__pendingValue=i.a,e(this)}if(this.__pendingValue===i.a)return;const e=this.__pendingValue,t=this.value,o=null==e||null!=t&&(e.capture!==t.capture||e.once!==t.once||e.passive!==t.passive),r=null!=e&&(null==t||o);o&&this.element.removeEventListener(this.eventName,this.__boundHandleEvent,this.__options),r&&(this.__options=y(e),this.element.addEventListener(this.eventName,this.__boundHandleEvent,this.__options)),this.value=e,this.__pendingValue=i.a}handleEvent(e){"function"==typeof this.value?this.value.call(this.eventContext||this.element,e):this.value.handleEvent(e)}}const y=e=>e&&(v?{capture:e.capture,passive:e.passive,once:e.once}:e.capture)},function(e,t,o){"use strict";o.d(t,"b",(function(){return a}));o(14);var n=o(1);o.d(t,"a",(function(){return n.c}));let r=0;const i={},a=(e,t,o)=>{const a=o&&o.moduleId||"custom-style-module-"+r++;Array.isArray(t)||(t=t?[t]:[]),t.forEach(e=>{if(!(e instanceof n.a))throw new Error("An item in styles is not of type CSSResult. Use `unsafeCSS` or `css`.");if(!i[e]){const t=document.createElement("dom-module");t.innerHTML=`\n        <template>\n          <style>${e.toString()}</style>\n        </template>\n      `;const o="custom-style-module-"+r++;t.register(o),i[e]=o}});const s=document.createElement("dom-module");if(e){const t=customElements.get(e);t&&Object.prototype.hasOwnProperty.call(t,"__finalized")&&console.warn(`The custom element definition for "${e}"\n      was finalized before a style module was registered.\n      Make sure to add component specific style modules before\n      importing the corresponding custom element.`),s.setAttribute("theme-for",e)}const l=o&&o.include||[];s.innerHTML=`\n    <template>\n      ${l.map(e=>`<style include=${e}></style>`)}\n      ${t.map(e=>`<style include=${i[e]}></style>`)}\n    </template>\n  `,s.register(a)}},function(e,t,o){"use strict";o.d(t,"a",(function(){return n})),o.d(t,"b",(function(){return r}));
/**
 * @license
 * Copyright (c) 2018 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const n={},r={}},function(e,t,o){"use strict";o.d(t,"a",(function(){return n})),o.d(t,"c",(function(){return r})),o.d(t,"b",(function(){return i}));
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const n="undefined"!=typeof window&&null!=window.customElements&&void 0!==window.customElements.polyfillWrapFlushCallback,r=(e,t,o=null,n=null)=>{for(;t!==o;){const o=t.nextSibling;e.insertBefore(t,n),t=o}},i=(e,t,o=null)=>{for(;t!==o;){const o=t.nextSibling;e.removeChild(t),t=o}}},function(e,t,o){"use strict";o.d(t,"c",(function(){return a.a})),o.d(t,"a",(function(){return n.b})),o.d(t,"b",(function(){return n.g})),o.d(t,"d",(function(){return s}));var n=o(2);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */const r=new class{handleAttributeExpressions(e,t,o,r){const i=t[0];if("."===i){return new n.f(e,t.slice(1),o).parts}if("@"===i)return[new n.d(e,t.slice(1),r.eventContext)];if("?"===i)return[new n.c(e,t.slice(1),o)];return new n.a(e,t,o).parts}handleTextExpression(e){return new n.e(e)}};var i=o(11),a=o(10);o(5),o(4),o(9),o(8),o(13),o(0);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
"undefined"!=typeof window&&(window.litHtmlVersions||(window.litHtmlVersions=[])).push("1.4.1");const s=(e,...t)=>new i.b(e,t,"html",r)},function(e,t,o){"use strict";var n,r;o.d(t,"a",(function(){return r})),function(e){e.CONNECTED="connected",e.LOADING="loading",e.RECONNECTING="reconnecting",e.CONNECTION_LOST="connection-lost"}(r||(r={}));class i{constructor(e){this.stateChangeListeners=new Set,this.loadingCount=0,this.connectionState=e,this.serviceWorkerMessageListener=this.serviceWorkerMessageListener.bind(this),navigator.serviceWorker&&(navigator.serviceWorker.addEventListener("message",this.serviceWorkerMessageListener),navigator.serviceWorker.ready.then(e=>{var t;null===(t=null==e?void 0:e.active)||void 0===t||t.postMessage({method:"Vaadin.ServiceWorker.isConnectionLost",id:"Vaadin.ServiceWorker.isConnectionLost"})}))}addStateChangeListener(e){this.stateChangeListeners.add(e)}removeStateChangeListener(e){this.stateChangeListeners.delete(e)}loadingStarted(){this.state=r.LOADING,this.loadingCount+=1}loadingFinished(){this.decreaseLoadingCount(r.CONNECTED)}loadingFailed(){this.decreaseLoadingCount(r.CONNECTION_LOST)}decreaseLoadingCount(e){this.loadingCount>0&&(this.loadingCount-=1,0===this.loadingCount&&(this.state=e))}get state(){return this.connectionState}set state(e){if(e!==this.connectionState){const t=this.connectionState;this.connectionState=e,this.loadingCount=0;for(const e of this.stateChangeListeners)e(t,this.connectionState)}}get online(){return this.connectionState===r.CONNECTED||this.connectionState===r.LOADING}get offline(){return!this.online}serviceWorkerMessageListener(e){"object"==typeof e.data&&"Vaadin.ServiceWorker.isConnectionLost"===e.data.id&&(!0===e.data.result&&(this.state=r.CONNECTION_LOST),navigator.serviceWorker.removeEventListener("message",this.serviceWorkerMessageListener))}}const a=window;(null===(n=a.Vaadin)||void 0===n?void 0:n.connectionState)||(a.Vaadin=a.Vaadin||{},a.Vaadin.connectionState=new i(navigator.onLine?r.CONNECTED:r.CONNECTION_LOST))},function(e,t,o){"use strict";o.d(t,"b",(function(){return r})),o.d(t,"a",(function(){return i}));var n=o(0);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */function r(e){let t=i.get(e.type);void 0===t&&(t={stringsArray:new WeakMap,keyString:new Map},i.set(e.type,t));let o=t.stringsArray.get(e.strings);if(void 0!==o)return o;const r=e.strings.join(n.f);return o=t.keyString.get(r),void 0===o&&(o=new n.a(e,e.getTemplateElement()),t.keyString.set(r,o)),t.stringsArray.set(e.strings,o),o}const i=new Map},function(e,t,o){"use strict";o.d(t,"a",(function(){return a})),o.d(t,"b",(function(){return s}));var n=o(5),r=o(2),i=o(8);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const a=new WeakMap,s=(e,t,o)=>{let s=a.get(t);void 0===s&&(Object(n.b)(t,t.firstChild),a.set(t,s=new r.e(Object.assign({templateFactory:i.b},o))),s.appendInto(t)),s.setValue(e),s.commit()}},function(e,t,o){"use strict";o.d(t,"a",(function(){return r})),o.d(t,"b",(function(){return i}));
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const n=new WeakMap,r=e=>(...t)=>{const o=e(...t);return n.set(o,!0),o},i=e=>"function"==typeof e&&n.has(e)},function(e,t,o){"use strict";o.d(t,"b",(function(){return s})),o.d(t,"a",(function(){return l}));var n=o(5),r=o(0);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
const i=window.trustedTypes&&trustedTypes.createPolicy("lit-html",{createHTML:e=>e}),a=` ${r.f} `;class s{constructor(e,t,o,n){this.strings=e,this.values=t,this.type=o,this.processor=n}getHTML(){const e=this.strings.length-1;let t="",o=!1;for(let n=0;n<e;n++){const e=this.strings[n],i=e.lastIndexOf("\x3c!--");o=(i>-1||o)&&-1===e.indexOf("--\x3e",i+1);const s=r.e.exec(e);t+=null===s?e+(o?a:r.g):e.substr(0,s.index)+s[1]+s[2]+r.b+s[3]+r.f}return t+=this.strings[e],t}getTemplateElement(){const e=document.createElement("template");let t=this.getHTML();return void 0!==i&&(t=i.createHTML(t)),e.innerHTML=t,e}}class l extends s{getHTML(){return`<svg>${super.getHTML()}</svg>`}getTemplateElement(){const e=super.getTemplateElement(),t=e.content,o=t.firstChild;return t.removeChild(o),Object(n.c)(t,o.firstChild),e}}},function(e,t,o){"use strict";o.d(t,"c",(function(){return s})),o.d(t,"b",(function(){return l})),o.d(t,"a",(function(){return c}));o(20);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/let n,r,i=/(url\()([^)]*)(\))/g,a=/(^\/)|(^#)|(^[\w-\d]*:)/;function s(e,t){if(e&&a.test(e))return e;if(void 0===n){n=!1;try{const e=new URL("b","http://a");e.pathname="c%20d",n="http://a/c%20d"===e.href}catch(e){}}return t||(t=document.baseURI||window.location.href),n?new URL(e,t).href:(r||(r=document.implementation.createHTMLDocument("temp"),r.base=r.createElement("base"),r.head.appendChild(r.base),r.anchor=r.createElement("a"),r.body.appendChild(r.anchor)),r.base.href=t,r.anchor.href=e,r.anchor.href||e)}function l(e,t){return e.replace(i,(function(e,o,n,r){return o+"'"+s(n.replace(/["']/g,""),t)+"'"+r}))}function c(e){return e.substring(0,e.lastIndexOf("/")+1)}},function(e,t,o){"use strict";o.d(t,"a",(function(){return i}));var n=o(5),r=o(0);
/**
 * @license
 * Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
class i{constructor(e,t,o){this.__parts=[],this.template=e,this.processor=t,this.options=o}update(e){let t=0;for(const o of this.__parts)void 0!==o&&o.setValue(e[t]),t++;for(const e of this.__parts)void 0!==e&&e.commit()}_clone(){const e=n.a?this.template.element.content.cloneNode(!0):document.importNode(this.template.element.content,!0),t=[],o=this.template.parts,i=document.createTreeWalker(e,133,null,!1);let a,s=0,l=0,c=i.nextNode();for(;s<o.length;)if(a=o[s],Object(r.d)(a)){for(;l<a.index;)l++,"TEMPLATE"===c.nodeName&&(t.push(c),i.currentNode=c.content),null===(c=i.nextNode())&&(i.currentNode=t.pop(),c=i.nextNode());if("node"===a.type){const e=this.processor.handleTextExpression(this.options);e.insertAfterNode(c.previousSibling),this.__parts.push(e)}else this.__parts.push(...this.processor.handleAttributeExpressions(c,a.name,a.strings,this.options));s++}else this.__parts.push(void 0),s++;return n.a&&(document.adoptNode(e),customElements.upgrade(e)),e}}},function(e,t,o){"use strict";o.d(t,"a",(function(){return c}));o(20);var n=o(12),r=o(23);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
let i={},a={};function s(e,t){i[e]=a[e.toLowerCase()]=t}function l(e){return i[e]||a[e.toLowerCase()]}class c extends HTMLElement{static get observedAttributes(){return["id"]}static import(e,t){if(e){let o=l(e);return o&&t?o.querySelector(t):o}return null}attributeChangedCallback(e,t,o,n){t!==o&&this.register()}get assetpath(){if(!this.__assetpath){const e=window.HTMLImports&&HTMLImports.importForElement?HTMLImports.importForElement(this)||document:this.ownerDocument,t=Object(n.c)(this.getAttribute("assetpath")||"",e.baseURI);this.__assetpath=Object(n.a)(t)}return this.__assetpath}register(e){if(e=e||this.id){if(r.f&&void 0!==l(e))throw s(e,null),new Error(`strictTemplatePolicy: dom-module ${e} re-registered`);this.id=e,s(e,this),(t=this).querySelector("style")&&console.warn("dom-module %s has style outside template",t.id)}var t}}c.prototype.modules=i,customElements.define("dom-module",c)},function(e,t,o){"use strict";o.d(t,"c",(function(){return n})),o.d(t,"b",(function(){return r})),o.d(t,"a",(function(){return i}));
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
const n=/(?:^|[;\s{]\s*)(--[\w-]*?)\s*:\s*(?:((?:'(?:\\'|.)*?'|"(?:\\"|.)*?"|\([^)]*?\)|[^};{])+)|\{([^}]*)\}(?:(?=[;\s}])|$))/gi,r=/(?:^|\W+)@apply\s*\(?([^);\n]*)\)?/gi,i=/@media\s(.*)/},function(e,t,o){"use strict";o.d(t,"d",(function(){return n})),o.d(t,"a",(function(){return i})),o.d(t,"b",(function(){return s})),o.d(t,"c",(function(){return l}));
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
const n=!(window.ShadyDOM&&window.ShadyDOM.inUse);let r,i;function a(e){r=(!e||!e.shimcssproperties)&&(n||Boolean(!navigator.userAgent.match(/AppleWebKit\/601|Edge\/15/)&&window.CSS&&CSS.supports&&CSS.supports("box-shadow","0 0 0 var(--foo)")))}window.ShadyCSS&&void 0!==window.ShadyCSS.cssBuild&&(i=window.ShadyCSS.cssBuild);const s=Boolean(window.ShadyCSS&&window.ShadyCSS.disableRuntime);window.ShadyCSS&&void 0!==window.ShadyCSS.nativeCss?r=window.ShadyCSS.nativeCss:window.ShadyCSS?(a(window.ShadyCSS),window.ShadyCSS=void 0):a(window.WebComponents&&window.WebComponents.flags);const l=r},function(e,t,o){"use strict";
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class n extends HTMLElement{static get version(){return"20.0.3"}}customElements.define("vaadin-lumo-styles",n)},function(e,t,o){"use strict";o.d(t,"c",(function(){return c})),o.d(t,"b",(function(){return d})),o.d(t,"a",(function(){return m}));var n=o(14),r=o(12);function i(e){return n.a.import(e)}function a(e){let t=e.body?e.body:e;const o=Object(r.b)(t.textContent,e.baseURI),n=document.createElement("style");return n.textContent=o,n}function s(e){const t=e.trim().split(/\s+/),o=[];for(let e=0;e<t.length;e++)o.push(...l(t[e]));return o}function l(e){const t=i(e);if(!t)return console.warn("Could not find style data in module named",e),[];if(void 0===t._styles){const e=[];e.push(...u(t));const o=t.querySelector("template");o&&e.push(...c(o,t.assetpath)),t._styles=e}return t._styles}function c(e,t){if(!e._styles){const o=[],n=e.content.querySelectorAll("style");for(let e=0;e<n.length;e++){let i=n[e],a=i.getAttribute("include");a&&o.push(...s(a).filter((function(e,t,o){return o.indexOf(e)===t}))),t&&(i.textContent=Object(r.b)(i.textContent,t)),o.push(i)}e._styles=o}return e._styles}function d(e){let t=i(e);return t?u(t):[]}function u(e){const t=[],o=e.querySelectorAll("link[rel=import][type~=css]");for(let e=0;e<o.length;e++){let n=o[e];if(n.import){const e=n.import,o=n.hasAttribute("shady-unscoped");if(o&&!e._unscopedStyle){const t=a(e);t.setAttribute("shady-unscoped",""),e._unscopedStyle=t}else e._style||(e._style=a(e));t.push(o?e._unscopedStyle:e._style)}}return t}function m(e){let t=e.trim().split(/\s+/),o="";for(let e=0;e<t.length;e++)o+=p(t[e]);return o}function p(e){let t=i(e);if(t&&void 0===t._cssText){let e=h(t),o=t.querySelector("template");o&&(e+=function(e,t){let o="";const n=c(e,t);for(let e=0;e<n.length;e++){let t=n[e];t.parentNode&&t.parentNode.removeChild(t),o+=t.textContent}return o}(o,t.assetpath)),t._cssText=e||null}return t||console.warn("Could not find style data in module named",e),t&&t._cssText||""}function h(e){let t="",o=u(e);for(let e=0;e<o.length;e++)t+=o[e].textContent;return t}},function(e,t,o){"use strict";o.d(t,"c",(function(){return r})),o.d(t,"b",(function(){return i})),o.d(t,"a",(function(){return a}));var n=o(15);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/function r(e,t){for(let o in t)null===o?e.style.removeProperty(o):e.style.setProperty(o,t[o])}function i(e,t){const o=window.getComputedStyle(e).getPropertyValue(t);return o?o.trim():""}function a(e){const t=n.b.test(e)||n.c.test(e);return n.b.lastIndex=0,n.c.lastIndex=0,t}},function(e,t,o){"use strict";
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/window.JSCompiler_renameProperty=function(e,t){return e}},function(e,t,o){"use strict";var n=o(3);o(17);
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
const r=n.a`
  :host {
    /* Base (background) */
    --lumo-base-color: #fff;

    /* Tint */
    --lumo-tint-5pct: hsla(0, 0%, 100%, 0.3);
    --lumo-tint-10pct: hsla(0, 0%, 100%, 0.37);
    --lumo-tint-20pct: hsla(0, 0%, 100%, 0.44);
    --lumo-tint-30pct: hsla(0, 0%, 100%, 0.5);
    --lumo-tint-40pct: hsla(0, 0%, 100%, 0.57);
    --lumo-tint-50pct: hsla(0, 0%, 100%, 0.64);
    --lumo-tint-60pct: hsla(0, 0%, 100%, 0.7);
    --lumo-tint-70pct: hsla(0, 0%, 100%, 0.77);
    --lumo-tint-80pct: hsla(0, 0%, 100%, 0.84);
    --lumo-tint-90pct: hsla(0, 0%, 100%, 0.9);
    --lumo-tint: #fff;

    /* Shade */
    --lumo-shade-5pct: hsla(214, 61%, 25%, 0.05);
    --lumo-shade-10pct: hsla(214, 57%, 24%, 0.1);
    --lumo-shade-20pct: hsla(214, 53%, 23%, 0.16);
    --lumo-shade-30pct: hsla(214, 50%, 22%, 0.26);
    --lumo-shade-40pct: hsla(214, 47%, 21%, 0.38);
    --lumo-shade-50pct: hsla(214, 45%, 20%, 0.5);
    --lumo-shade-60pct: hsla(214, 43%, 19%, 0.61);
    --lumo-shade-70pct: hsla(214, 42%, 18%, 0.72);
    --lumo-shade-80pct: hsla(214, 41%, 17%, 0.83);
    --lumo-shade-90pct: hsla(214, 40%, 16%, 0.94);
    --lumo-shade: hsl(214, 35%, 15%);

    /* Contrast */
    --lumo-contrast-5pct: var(--lumo-shade-5pct);
    --lumo-contrast-10pct: var(--lumo-shade-10pct);
    --lumo-contrast-20pct: var(--lumo-shade-20pct);
    --lumo-contrast-30pct: var(--lumo-shade-30pct);
    --lumo-contrast-40pct: var(--lumo-shade-40pct);
    --lumo-contrast-50pct: var(--lumo-shade-50pct);
    --lumo-contrast-60pct: var(--lumo-shade-60pct);
    --lumo-contrast-70pct: var(--lumo-shade-70pct);
    --lumo-contrast-80pct: var(--lumo-shade-80pct);
    --lumo-contrast-90pct: var(--lumo-shade-90pct);
    --lumo-contrast: var(--lumo-shade);

    /* Text */
    --lumo-header-text-color: var(--lumo-contrast);
    --lumo-body-text-color: var(--lumo-contrast-90pct);
    --lumo-secondary-text-color: var(--lumo-contrast-70pct);
    --lumo-tertiary-text-color: var(--lumo-contrast-50pct);
    --lumo-disabled-text-color: var(--lumo-contrast-30pct);

    /* Primary */
    --lumo-primary-color: hsl(214, 90%, 52%);
    --lumo-primary-color-50pct: hsla(214, 90%, 52%, 0.5);
    --lumo-primary-color-10pct: hsla(214, 90%, 52%, 0.1);
    --lumo-primary-text-color: var(--lumo-primary-color);
    --lumo-primary-contrast-color: #fff;

    /* Error */
    --lumo-error-color: hsl(3, 100%, 61%);
    --lumo-error-color-50pct: hsla(3, 100%, 60%, 0.5);
    --lumo-error-color-10pct: hsla(3, 100%, 60%, 0.1);
    --lumo-error-text-color: hsl(3, 92%, 53%);
    --lumo-error-contrast-color: #fff;

    /* Success */
    --lumo-success-color: hsl(145, 80%, 42%); /* hsl(144,82%,37%); */
    --lumo-success-color-50pct: hsla(145, 76%, 44%, 0.55);
    --lumo-success-color-10pct: hsla(145, 76%, 44%, 0.12);
    --lumo-success-text-color: hsl(145, 100%, 32%);
    --lumo-success-contrast-color: #fff;
  }
`,i=document.createElement("template");i.innerHTML=`<style>${r.toString().replace(":host","html")}</style>`,document.head.appendChild(i.content);const a=n.a`
  [theme~='dark'] {
    /* Base (background) */
    --lumo-base-color: hsl(214, 35%, 21%);

    /* Tint */
    --lumo-tint-5pct: hsla(214, 65%, 85%, 0.06);
    --lumo-tint-10pct: hsla(214, 60%, 80%, 0.14);
    --lumo-tint-20pct: hsla(214, 64%, 82%, 0.23);
    --lumo-tint-30pct: hsla(214, 69%, 84%, 0.32);
    --lumo-tint-40pct: hsla(214, 73%, 86%, 0.41);
    --lumo-tint-50pct: hsla(214, 78%, 88%, 0.5);
    --lumo-tint-60pct: hsla(214, 82%, 90%, 0.6);
    --lumo-tint-70pct: hsla(214, 87%, 92%, 0.7);
    --lumo-tint-80pct: hsla(214, 91%, 94%, 0.8);
    --lumo-tint-90pct: hsla(214, 96%, 96%, 0.9);
    --lumo-tint: hsl(214, 100%, 98%);

    /* Shade */
    --lumo-shade-5pct: hsla(214, 0%, 0%, 0.07);
    --lumo-shade-10pct: hsla(214, 4%, 2%, 0.15);
    --lumo-shade-20pct: hsla(214, 8%, 4%, 0.23);
    --lumo-shade-30pct: hsla(214, 12%, 6%, 0.32);
    --lumo-shade-40pct: hsla(214, 16%, 8%, 0.41);
    --lumo-shade-50pct: hsla(214, 20%, 10%, 0.5);
    --lumo-shade-60pct: hsla(214, 24%, 12%, 0.6);
    --lumo-shade-70pct: hsla(214, 28%, 13%, 0.7);
    --lumo-shade-80pct: hsla(214, 32%, 13%, 0.8);
    --lumo-shade-90pct: hsla(214, 33%, 13%, 0.9);
    --lumo-shade: hsl(214, 33%, 13%);

    /* Contrast */
    --lumo-contrast-5pct: var(--lumo-tint-5pct);
    --lumo-contrast-10pct: var(--lumo-tint-10pct);
    --lumo-contrast-20pct: var(--lumo-tint-20pct);
    --lumo-contrast-30pct: var(--lumo-tint-30pct);
    --lumo-contrast-40pct: var(--lumo-tint-40pct);
    --lumo-contrast-50pct: var(--lumo-tint-50pct);
    --lumo-contrast-60pct: var(--lumo-tint-60pct);
    --lumo-contrast-70pct: var(--lumo-tint-70pct);
    --lumo-contrast-80pct: var(--lumo-tint-80pct);
    --lumo-contrast-90pct: var(--lumo-tint-90pct);
    --lumo-contrast: var(--lumo-tint);

    /* Text */
    --lumo-header-text-color: var(--lumo-contrast);
    --lumo-body-text-color: var(--lumo-contrast-90pct);
    --lumo-secondary-text-color: var(--lumo-contrast-70pct);
    --lumo-tertiary-text-color: var(--lumo-contrast-50pct);
    --lumo-disabled-text-color: var(--lumo-contrast-30pct);

    /* Primary */
    --lumo-primary-color: hsl(214, 86%, 55%);
    --lumo-primary-color-50pct: hsla(214, 86%, 55%, 0.5);
    --lumo-primary-color-10pct: hsla(214, 90%, 63%, 0.1);
    --lumo-primary-text-color: hsl(214, 100%, 70%);
    --lumo-primary-contrast-color: #fff;

    /* Error */
    --lumo-error-color: hsl(3, 90%, 63%);
    --lumo-error-color-50pct: hsla(3, 90%, 63%, 0.5);
    --lumo-error-color-10pct: hsla(3, 90%, 63%, 0.1);
    --lumo-error-text-color: hsl(3, 100%, 67%);

    /* Success */
    --lumo-success-color: hsl(145, 65%, 42%);
    --lumo-success-color-50pct: hsla(145, 65%, 42%, 0.5);
    --lumo-success-color-10pct: hsla(145, 65%, 42%, 0.1);
    --lumo-success-text-color: hsl(145, 85%, 47%);
  }

  html {
    color: var(--lumo-body-text-color);
    background-color: var(--lumo-base-color);
  }

  [theme~='dark'] {
    color: var(--lumo-body-text-color);
    background-color: var(--lumo-base-color);
  }

  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    color: var(--lumo-header-text-color);
  }

  a {
    color: var(--lumo-primary-text-color);
  }

  blockquote {
    color: var(--lumo-secondary-text-color);
  }

  code,
  pre {
    background-color: var(--lumo-contrast-10pct);
    border-radius: var(--lumo-border-radius-m);
  }
`;Object(n.b)("",a,{moduleId:"lumo-color"});const s=n.a`
  :host {
    color: var(--lumo-body-text-color) !important;
    background-color: var(--lumo-base-color) !important;
  }
`;Object(n.b)("",s,{moduleId:"lumo-color-legacy",include:["lumo-color"]})},function(e,t,o){"use strict";var n=o(3);o(17);
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
const r=n.a`
  :host {
    /* prettier-ignore */
    --lumo-font-family: -apple-system, BlinkMacSystemFont, 'Roboto', 'Segoe UI', Helvetica, Arial, sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';

    /* Font sizes */
    --lumo-font-size-xxs: 0.75rem;
    --lumo-font-size-xs: 0.8125rem;
    --lumo-font-size-s: 0.875rem;
    --lumo-font-size-m: 1rem;
    --lumo-font-size-l: 1.125rem;
    --lumo-font-size-xl: 1.375rem;
    --lumo-font-size-xxl: 1.75rem;
    --lumo-font-size-xxxl: 2.5rem;

    /* Line heights */
    --lumo-line-height-xs: 1.25;
    --lumo-line-height-s: 1.375;
    --lumo-line-height-m: 1.625;
  }
`,i=document.createElement("template");i.innerHTML=`<style>${r.toString().replace(":host","html")}</style>`,document.head.appendChild(i.content);const a=n.a`
  html {
    font-family: var(--lumo-font-family);
    font-size: var(--lumo-font-size, var(--lumo-font-size-m));
    line-height: var(--lumo-line-height-m);
    -webkit-text-size-adjust: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  /* Can’t combine with the above selector because that doesn’t work in browsers without native shadow dom */
  :host {
    font-family: var(--lumo-font-family);
    font-size: var(--lumo-font-size, var(--lumo-font-size-m));
    line-height: var(--lumo-line-height-m);
    -webkit-text-size-adjust: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  small,
  [theme~='font-size-s'] {
    font-size: var(--lumo-font-size-s);
    line-height: var(--lumo-line-height-s);
  }

  [theme~='font-size-xs'] {
    font-size: var(--lumo-font-size-xs);
    line-height: var(--lumo-line-height-xs);
  }

  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    font-weight: 600;
    line-height: var(--lumo-line-height-xs);
    margin-top: 1.25em;
  }

  h1 {
    font-size: var(--lumo-font-size-xxxl);
    margin-bottom: 0.75em;
  }

  h2 {
    font-size: var(--lumo-font-size-xxl);
    margin-bottom: 0.5em;
  }

  h3 {
    font-size: var(--lumo-font-size-xl);
    margin-bottom: 0.5em;
  }

  h4 {
    font-size: var(--lumo-font-size-l);
    margin-bottom: 0.5em;
  }

  h5 {
    font-size: var(--lumo-font-size-m);
    margin-bottom: 0.25em;
  }

  h6 {
    font-size: var(--lumo-font-size-xs);
    margin-bottom: 0;
    text-transform: uppercase;
    letter-spacing: 0.03em;
  }

  p,
  blockquote {
    margin-top: 0.5em;
    margin-bottom: 0.75em;
  }

  a {
    text-decoration: none;
  }

  a:hover {
    text-decoration: underline;
  }

  hr {
    display: block;
    align-self: stretch;
    height: 1px;
    border: 0;
    padding: 0;
    margin: var(--lumo-space-s) calc(var(--lumo-border-radius-m) / 2);
    background-color: var(--lumo-contrast-10pct);
  }

  blockquote {
    border-left: 2px solid var(--lumo-contrast-30pct);
  }

  b,
  strong {
    font-weight: 600;
  }

  /* RTL specific styles */

  blockquote[dir='rtl'] {
    border-left: none;
    border-right: 2px solid var(--lumo-contrast-30pct);
  }
`;Object(n.b)("",a,{moduleId:"lumo-typography"})},function(e,t,o){"use strict";o.d(t,"h",(function(){return r})),o.d(t,"d",(function(){return i})),o.d(t,"e",(function(){return a})),o.d(t,"c",(function(){return s})),o.d(t,"f",(function(){return l})),o.d(t,"a",(function(){return c})),o.d(t,"b",(function(){return d})),o.d(t,"g",(function(){return u}));o(20);var n=o(12);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
const r=!window.ShadyDOM;Boolean(!window.ShadyCSS||window.ShadyCSS.nativeCss),window.customElements.polyfillWrapFlushCallback;let i=Object(n.a)(document.baseURI||window.location.href);let a=window.Polymer&&window.Polymer.sanitizeDOMValue||void 0;let s=!1;let l=!1;let c=!1;let d=!1;let u=!1},function(e,t,o){"use strict";o.d(t,"a",(function(){return d}));var n=o(1),r=o(6);
/**
 * @license
 * Copyright (c) 2018 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */
class i{constructor(e){this.classes=new Set,this.changed=!1,this.element=e;const t=(e.getAttribute("class")||"").split(/\s+/);for(const e of t)this.classes.add(e)}add(e){this.classes.add(e),this.changed=!0}remove(e){this.classes.delete(e),this.changed=!0}commit(){if(this.changed){let e="";this.classes.forEach(t=>e+=t+" "),this.element.setAttribute("class",e)}}}const a=new WeakMap,s=Object(r.c)(e=>t=>{if(!(t instanceof r.a)||t instanceof r.b||"class"!==t.committer.name||t.committer.parts.length>1)throw new Error("The `classMap` directive must be used in the `class` attribute and must be the only part in the attribute.");const{committer:o}=t,{element:n}=o;let s=a.get(t);void 0===s&&(n.setAttribute("class",o.strings.join(" ")),a.set(t,s=new Set));const l=n.classList||new i(n);s.forEach(t=>{t in e||(l.remove(t),s.delete(t))});for(const t in e){const o=e[t];o!=s.has(t)&&(o?(l.add(t),s.add(t)):(l.remove(t),s.delete(t)))}"function"==typeof l.commit&&l.commit()});var l=o(7),c=function(e,t,o,n){var r,i=arguments.length,a=i<3?t:null===n?n=Object.getOwnPropertyDescriptor(t,o):n;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)a=Reflect.decorate(e,t,o,n);else for(var s=e.length-1;s>=0;s--)(r=e[s])&&(a=(i<3?r(a):i>3?r(t,o,a):r(t,o))||a);return i>3&&a&&Object.defineProperty(t,o,a),a};class d extends n.b{constructor(){super(),this.firstDelay=300,this.secondDelay=1500,this.thirdDelay=5e3,this.expandedDuration=2e3,this.onlineText="Online",this.offlineText="Connection lost",this.reconnectingText="Connection lost, trying to reconnect...",this.offline=!1,this.reconnecting=!1,this.expanded=!1,this.loading=!1,this.loadingBarState="",this.applyDefaultThemeState=!0,this.firstTimeout=0,this.secondTimeout=0,this.thirdTimeout=0,this.expandedTimeout=0,this.lastMessageState=l.a.CONNECTED,this.connectionStateListener=()=>{this.expanded=this.updateConnectionState(),this.expandedTimeout=this.timeoutFor(this.expandedTimeout,this.expanded,()=>this.expanded=!1,this.expandedDuration)}}static create(){var e,t;const o=window;return(null===(e=o.Vaadin)||void 0===e?void 0:e.connectionIndicator)||(o.Vaadin=o.Vaadin||{},o.Vaadin.connectionIndicator=document.createElement("vaadin-connection-indicator"),document.body.appendChild(o.Vaadin.connectionIndicator)),null===(t=o.Vaadin)||void 0===t?void 0:t.connectionIndicator}render(){return n.d`
      <div class="v-loading-indicator ${this.loadingBarState}" style="${this.getLoadingBarStyle()}"></div>

      <div
        class="v-status-message ${s({active:this.reconnecting})}"
      >
        <span class="text"> ${this.renderMessage()} </span>
      </div>
    `}connectedCallback(){var e;super.connectedCallback();const t=window;(null===(e=t.Vaadin)||void 0===e?void 0:e.connectionState)&&(this.connectionStateStore=t.Vaadin.connectionState,this.connectionStateStore.addStateChangeListener(this.connectionStateListener),this.updateConnectionState()),this.updateTheme()}disconnectedCallback(){super.disconnectedCallback(),this.connectionStateStore&&this.connectionStateStore.removeStateChangeListener(this.connectionStateListener),this.updateTheme()}get applyDefaultTheme(){return this.applyDefaultThemeState}set applyDefaultTheme(e){e!==this.applyDefaultThemeState&&(this.applyDefaultThemeState=e,this.updateTheme())}createRenderRoot(){return this}updateConnectionState(){var e;const t=null===(e=this.connectionStateStore)||void 0===e?void 0:e.state;return this.offline=t===l.a.CONNECTION_LOST,this.reconnecting=t===l.a.RECONNECTING,this.updateLoading(t===l.a.LOADING),!this.loading&&(t!==this.lastMessageState&&(this.lastMessageState=t,!0))}updateLoading(e){this.loading=e,this.loadingBarState="",this.firstTimeout=this.timeoutFor(this.firstTimeout,e,()=>this.loadingBarState="first",this.firstDelay),this.secondTimeout=this.timeoutFor(this.secondTimeout,e,()=>this.loadingBarState="second",this.secondDelay),this.thirdTimeout=this.timeoutFor(this.thirdTimeout,e,()=>this.loadingBarState="third",this.thirdDelay)}renderMessage(){return this.reconnecting?this.reconnectingText:this.offline?this.offlineText:this.onlineText}updateTheme(){if(this.applyDefaultThemeState&&this.isConnected){if(!document.getElementById("css-loading-indicator")){const e=document.createElement("style");e.id="css-loading-indicator",e.textContent=this.getDefaultStyle().cssText,document.head.appendChild(e)}}else{const e=document.getElementById("css-loading-indicator");e&&document.head.removeChild(e)}}getDefaultStyle(){return n.c`
      @keyframes v-progress-start {
        0% {
          width: 0%;
        }
        100% {
          width: 50%;
        }
      }
      @keyframes v-progress-delay {
        0% {
          width: 50%;
        }
        100% {
          width: 90%;
        }
      }
      @keyframes v-progress-wait {
        0% {
          width: 90%;
          height: 4px;
        }
        3% {
          width: 91%;
          height: 7px;
        }
        100% {
          width: 96%;
          height: 7px;
        }
      }
      @keyframes v-progress-wait-pulse {
        0% {
          opacity: 1;
        }
        50% {
          opacity: 0.1;
        }
        100% {
          opacity: 1;
        }
      }
      .v-loading-indicator,
      .v-status-message {
        position: fixed;
        z-index: 251;
        left: 0;
        right: auto;
        top: 0;
        background-color: var(--lumo-primary-color, var(--material-primary-color, blue));
        transition: none;
      }
      .v-loading-indicator {
        width: 50%;
        height: 4px;
        opacity: 1;
        pointer-events: none;
        animation: v-progress-start 1000ms 200ms both;
      }
      .v-loading-indicator[style*='none'] {
        display: block !important;
        width: 100%;
        opacity: 0;
        animation: none;
        transition: opacity 500ms 300ms, width 300ms;
      }
      .v-loading-indicator.second {
        width: 90%;
        animation: v-progress-delay 3.8s forwards;
      }
      .v-loading-indicator.third {
        width: 96%;
        animation: v-progress-wait 5s forwards, v-progress-wait-pulse 1s 4s infinite backwards;
      }

      vaadin-connection-indicator[offline] .v-loading-indicator,
      vaadin-connection-indicator[reconnecting] .v-loading-indicator {
        display: none;
      }

      .v-status-message {
        opacity: 0;
        width: 100%;
        max-height: var(--status-height-collapsed, 8px);
        overflow: hidden;
        background-color: var(--status-bg-color-online, var(--lumo-primary-color, var(--material-primary-color, blue)));
        color: var(
          --status-text-color-online,
          var(--lumo-primary-contrast-color, var(--material-primary-contrast-color, #fff))
        );
        font-size: 0.75rem;
        font-weight: 600;
        line-height: 1;
        transition: all 0.5s;
        padding: 0 0.5em;
      }

      vaadin-connection-indicator[offline] .v-status-message,
      vaadin-connection-indicator[reconnecting] .v-status-message {
        opacity: 1;
        background-color: var(--status-bg-color-offline, var(--lumo-shade, #333));
        color: var(
          --status-text-color-offline,
          var(--lumo-primary-contrast-color, var(--material-primary-contrast-color, #fff))
        );
        background-image: repeating-linear-gradient(
          45deg,
          rgba(255, 255, 255, 0),
          rgba(255, 255, 255, 0) 10px,
          rgba(255, 255, 255, 0.1) 10px,
          rgba(255, 255, 255, 0.1) 20px
        );
      }

      vaadin-connection-indicator[reconnecting] .v-status-message {
        animation: show-reconnecting-status 2s;
      }

      vaadin-connection-indicator[offline] .v-status-message:hover,
      vaadin-connection-indicator[reconnecting] .v-status-message:hover,
      vaadin-connection-indicator[expanded] .v-status-message {
        max-height: var(--status-height, 1.75rem);
      }

      vaadin-connection-indicator[expanded] .v-status-message {
        opacity: 1;
      }

      .v-status-message span {
        display: flex;
        align-items: center;
        justify-content: center;
        height: var(--status-height, 1.75rem);
      }

      vaadin-connection-indicator[reconnecting] .v-status-message span::before {
        content: '';
        width: 1em;
        height: 1em;
        border-top: 2px solid
          var(--status-spinner-color, var(--lumo-primary-color, var(--material-primary-color, blue)));
        border-left: 2px solid
          var(--status-spinner-color, var(--lumo-primary-color, var(--material-primary-color, blue)));
        border-right: 2px solid transparent;
        border-bottom: 2px solid transparent;
        border-radius: 50%;
        box-sizing: border-box;
        animation: v-spin 0.4s linear infinite;
        margin: 0 0.5em;
      }

      @keyframes v-spin {
        100% {
          transform: rotate(360deg);
        }
      }
    `}getLoadingBarStyle(){switch(this.loadingBarState){case"":return"display: none";case"first":case"second":case"third":return"display: block";default:return""}}timeoutFor(e,t,o,n){return 0!==e&&window.clearTimeout(e),t?window.setTimeout(o,n):0}static get instance(){return d.create()}}c([Object(n.e)({type:Number})],d.prototype,"firstDelay",void 0),c([Object(n.e)({type:Number})],d.prototype,"secondDelay",void 0),c([Object(n.e)({type:Number})],d.prototype,"thirdDelay",void 0),c([Object(n.e)({type:Number})],d.prototype,"expandedDuration",void 0),c([Object(n.e)({type:String})],d.prototype,"onlineText",void 0),c([Object(n.e)({type:String})],d.prototype,"offlineText",void 0),c([Object(n.e)({type:String})],d.prototype,"reconnectingText",void 0),c([Object(n.e)({type:Boolean,reflect:!0})],d.prototype,"offline",void 0),c([Object(n.e)({type:Boolean,reflect:!0})],d.prototype,"reconnecting",void 0),c([Object(n.e)({type:Boolean,reflect:!0})],d.prototype,"expanded",void 0),c([Object(n.e)({type:Boolean,reflect:!0})],d.prototype,"loading",void 0),c([Object(n.e)({type:String})],d.prototype,"loadingBarState",void 0),c([Object(n.e)({type:Boolean})],d.prototype,"applyDefaultTheme",null),void 0===customElements.get("vaadin-connection-indicator")&&customElements.define("vaadin-connection-indicator",d);d.instance},function(e,t,o){"use strict";o.d(t,"a",(function(){return d}));
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
let n,r=null,i=window.HTMLImports&&window.HTMLImports.whenReady||null;function a(e){requestAnimationFrame((function(){i?i(e):(r||(r=new Promise(e=>{n=e}),"complete"===document.readyState?n():document.addEventListener("readystatechange",()=>{"complete"===document.readyState&&n()})),r.then((function(){e&&e()})))}))}
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const s="__shadyCSSCachedStyle";let l=null,c=null;class d{constructor(){this.customStyles=[],this.enqueued=!1,a(()=>{window.ShadyCSS.flushCustomStyles&&window.ShadyCSS.flushCustomStyles()})}enqueueDocumentValidation(){!this.enqueued&&c&&(this.enqueued=!0,a(c))}addCustomStyle(e){e.__seenByShadyCSS||(e.__seenByShadyCSS=!0,this.customStyles.push(e),this.enqueueDocumentValidation())}getStyleForCustomStyle(e){if(e[s])return e[s];let t;return t=e.getStyle?e.getStyle():e,t}processStyles(){const e=this.customStyles;for(let t=0;t<e.length;t++){const o=e[t];if(o[s])continue;const n=this.getStyleForCustomStyle(o);if(n){const e=n.__appliedElement||n;l&&l(e),o[s]=e}}return e}}d.prototype.addCustomStyle=d.prototype.addCustomStyle,d.prototype.getStyleForCustomStyle=d.prototype.getStyleForCustomStyle,d.prototype.processStyles=d.prototype.processStyles,Object.defineProperties(d.prototype,{transformCallback:{get:()=>l,set(e){l=e}},validateCallback:{get:()=>c,set(e){let t=!1;c||(t=!0),c=e,t&&this.enqueueDocumentValidation()}}})},function(e,t,o){"use strict";var n=o(3);o(17);
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
const r=n.a`
  :host {
    /* Square */
    --lumo-space-xs: 0.25rem;
    --lumo-space-s: 0.5rem;
    --lumo-space-m: 1rem;
    --lumo-space-l: 1.5rem;
    --lumo-space-xl: 2.5rem;

    /* Wide */
    --lumo-space-wide-xs: calc(var(--lumo-space-xs) / 2) var(--lumo-space-xs);
    --lumo-space-wide-s: calc(var(--lumo-space-s) / 2) var(--lumo-space-s);
    --lumo-space-wide-m: calc(var(--lumo-space-m) / 2) var(--lumo-space-m);
    --lumo-space-wide-l: calc(var(--lumo-space-l) / 2) var(--lumo-space-l);
    --lumo-space-wide-xl: calc(var(--lumo-space-xl) / 2) var(--lumo-space-xl);

    /* Tall */
    --lumo-space-tall-xs: var(--lumo-space-xs) calc(var(--lumo-space-xs) / 2);
    --lumo-space-tall-s: var(--lumo-space-s) calc(var(--lumo-space-s) / 2);
    --lumo-space-tall-m: var(--lumo-space-m) calc(var(--lumo-space-m) / 2);
    --lumo-space-tall-l: var(--lumo-space-l) calc(var(--lumo-space-l) / 2);
    --lumo-space-tall-xl: var(--lumo-space-xl) calc(var(--lumo-space-xl) / 2);
  }
`,i=document.createElement("template");i.innerHTML=`<style>${r.toString().replace(":host","html")}</style>`,document.head.appendChild(i.content)},function(e,t,o){"use strict";var n=o(3);o(17);
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
const r=n.a`
  :host {
    /* Border radius */
    --lumo-border-radius-s: 0.25em; /* Checkbox, badge, date-picker year indicator, etc */
    --lumo-border-radius-m: var(--lumo-border-radius, 0.25em); /* Button, text field, menu overlay, etc */
    --lumo-border-radius-l: 0.5em; /* Dialog, notification, etc */
    --lumo-border-radius: 0.25em; /* Deprecated */

    /* Shadow */
    --lumo-box-shadow-xs: 0 1px 4px -1px var(--lumo-shade-50pct);
    --lumo-box-shadow-s: 0 2px 4px -1px var(--lumo-shade-20pct), 0 3px 12px -1px var(--lumo-shade-30pct);
    --lumo-box-shadow-m: 0 2px 6px -1px var(--lumo-shade-20pct), 0 8px 24px -4px var(--lumo-shade-40pct);
    --lumo-box-shadow-l: 0 3px 18px -2px var(--lumo-shade-20pct), 0 12px 48px -6px var(--lumo-shade-40pct);
    --lumo-box-shadow-xl: 0 4px 24px -3px var(--lumo-shade-20pct), 0 18px 64px -8px var(--lumo-shade-40pct);

    /* Clickable element cursor */
    --lumo-clickable-cursor: default;
  }
`,i=document.createElement("template");i.innerHTML=`<style>${r.toString().replace(":host","html")}</style>`,document.head.appendChild(i.content)},function(e,t,o){"use strict";var n=o(25),r=o(19),i=o(16);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/
const a=new n.a;window.ShadyCSS||(window.ShadyCSS={prepareTemplate(e,t,o){},prepareTemplateDom(e,t){},prepareTemplateStyles(e,t,o){},styleSubtree(e,t){a.processStyles(),Object(r.c)(e,t)},styleElement(e){a.processStyles()},styleDocument(e){a.processStyles(),Object(r.c)(document.body,e)},getComputedStyleValue:(e,t)=>Object(r.b)(e,t),flushCustomStyles(){},nativeCss:i.c,nativeShadow:i.d,cssBuild:i.a,disableRuntime:i.b}),window.ShadyCSS.CustomStyleInterface=a;var s=o(18);
/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const l=window.ShadyCSS.CustomStyleInterface;class c extends HTMLElement{constructor(){super(),this._style=null,l.addCustomStyle(this)}getStyle(){if(this._style)return this._style;const e=this.querySelector("style");if(!e)return null;this._style=e;const t=e.getAttribute("include");return t&&(e.removeAttribute("include"),e.textContent=Object(s.a)(t)+e.textContent),this.ownerDocument!==window.document&&window.document.head.appendChild(this),this._style}}window.customElements.define("custom-style",c)},function(e,t){!function(){"use strict";if(!("adoptedStyleSheets"in document)){var e="ShadyCSS"in window&&!window.ShadyCSS.nativeShadow,t=[],o=[],n=[],r=new WeakMap,i=new WeakMap,a=new WeakMap,s=new WeakMap,l=new WeakMap,c=new WeakMap,d={loaded:!1},u={body:null,CSSStyleSheet:null},m=CSSStyleSheet,p=/@import/,h=function(){function e(){var e=document.createElement("style");d.loaded?u.body.appendChild(e):(document.head.appendChild(e),e.disabled=!0,t.push(e)),i.set(this,{adopters:new Map,actions:[],basicStyleElement:e})}var o,n,r,a=e.prototype;return a.replace=function(e){var t=x(e);try{if(!i.has(this))throw new TypeError("Illegal invocation");return i.get(this).basicStyleElement.innerHTML=t,b(this),Promise.resolve(this)}catch(e){return Promise.reject(e)}},a.replaceSync=function(e){var t=x(e);if(!i.has(this))throw new TypeError("Illegal invocation");return i.get(this).basicStyleElement.innerHTML=t,b(this),this},o=e,(n=[{key:"cssRules",get:function(){if(!i.has(this))throw new TypeError("Illegal invocation");return i.get(this).basicStyleElement.sheet.cssRules}}])&&g(o.prototype,n),r&&g(o,r),e}();["addImport","addPageRule","addRule","deleteRule","insertRule","removeImport","removeRule"].forEach((function(e){h.prototype[e]=function(){if(!i.has(this))throw new TypeError("Illegal invocation");var t=arguments,o=i.get(this),n=o.adopters,r=o.actions,a=o.basicStyleElement,s=a.sheet[e].apply(a.sheet,t);return n.forEach((function(o){o.sheet&&o.sheet[e].apply(o.sheet,t)})),r.push([e,t]),s}})),Object.defineProperty(h,Symbol.hasInstance,{configurable:!0,value:w}),y(m.prototype),window.CSSStyleSheet=h,function(){var t={configurable:!0,get:function(){return r.get(this)||[]},set:function(e){var t=r.get(this)||[];!function(e,t){var o=t===document?"Document":"ShadowRoot";if(!Array.isArray(e))throw new TypeError("Failed to set the 'adoptedStyleSheets' property on "+o+": Iterator getter is not callable.");if(!e.every(w))throw new TypeError("Failed to set the 'adoptedStyleSheets' property on "+o+": Failed to convert value to 'CSSStyleSheet'");var n=e.filter((function(t,o){return e.indexOf(t)===o}));r.set(t,n)}(e,this);var o=this===document?f()?this.head:this.body:this;("isConnected"in o?o.isConnected:document.body.contains(o))?window.requestAnimationFrame((function(){_(o),function(e,t){for(var o=v(e),n=0,r=t.length;n<r;n++)if(!(o.indexOf(t[n])>-1)){var a=i.get(t[n]).adopters,s=l.get(e),c=a.get(e);c||(c=a.get(document.head)),s.disconnect(),c.parentNode.removeChild(c),s.observe()}}(o,t)})):n.push(o)}};if(Object.defineProperty(Document.prototype,"adoptedStyleSheets",t),"undefined"!=typeof ShadowRoot){var o=Element.prototype.attachShadow;Element.prototype.attachShadow=function(){var t=e?this:o.apply(this,arguments);return s.set(this,t),C(t),t},Object.defineProperty(ShadowRoot.prototype,"adoptedStyleSheets",t)}}(),f()?document.addEventListener("DOMContentLoaded",E):E()}function g(e,t){for(var o=0;o<t.length;o++){var n=t[o];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function f(){return"loading"===document.readyState}function v(e){return r.get(e.parentNode===document.documentElement?document:e)}function x(e){void 0===e&&(e="");var t=e.match(p)||[],o=e;return t.length&&(console.warn("@import rules are not allowed here. See https://github.com/WICG/construct-stylesheets/issues/119#issuecomment-588352418"),t.forEach((function(e){o=o.replace(e,"")}))),o}function y(e){e.replace=function(){return Promise.reject(new DOMException("Can't call replace on non-constructed CSSStyleSheets."))},e.replaceSync=function(){throw new DOMException("Failed to execute 'replaceSync' on 'CSSStyleSheet': Can't call replaceSync on non-constructed CSSStyleSheets.")}}function b(e){var t=i.get(e),o=t.adopters,n=t.basicStyleElement;o.forEach((function(e){e.innerHTML=n.innerHTML}))}function w(e){return e&&e.constructor===h||e instanceof m||e instanceof u.CSSStyleSheet}function _(e){for(var t=document.createDocumentFragment(),n=v(e),r=l.get(e),s=0,d=n.length;s<d;s++){var u=i.get(n[s]),m=u.adopters,p=u.basicStyleElement,h=m.get(e);h?(r.disconnect(),t.appendChild(h),(!h.innerHTML||h.sheet&&!h.sheet.cssText)&&(h.innerHTML=p.innerHTML),r.observe()):((h=document.createElement("style")).innerHTML=p.innerHTML,a.set(h,e),c.set(h,0),m.set(e,h),t.appendChild(h)),e===document.head&&o.push(h)}e.insertBefore(t,e.lastChild);for(var g=0,f=n.length;g<f;g++){var x=i.get(n[g]),y=x.adopters,b=x.actions,w=y.get(e),_=c.get(w);if(b.length>0){for(var S=_,C=b.length;S<C;S++){var E=b[S],T=E[0],k=E[1];w.sheet[T].apply(w.sheet,k)}c.set(w,b.length-1)}}}function S(t){if(document)for(var o=0,n=t.length;o<n;o++){for(var r=t[o],i=r.addedNodes,l=r.removedNodes,c=0,d=l.length;c<d;c++){var u=a.get(l[c]);u&&_(u)}if(!e)for(var m=0,p=i.length;m<p;m++)for(var h=document.createNodeIterator(i[m],NodeFilter.SHOW_ELEMENT,(function(e){var t=s.get(e);return t&&t.adoptedStyleSheets.length>0?NodeFilter.FILTER_ACCEPT:NodeFilter.FILTER_REJECT}),null,!1),g=void 0;g=h.nextNode();)_(s.get(g))}}function C(e){var t=new MutationObserver(S),o={observe:function(){t.observe(e,{childList:!0,subtree:!0})},disconnect:function(){t.disconnect()}};l.set(e,o),o.observe()}function E(){var e=document.createElement("iframe");e.hidden=!0,document.body.appendChild(e),u.body=e.contentWindow.document.body,u.CSSStyleSheet=e.contentWindow.CSSStyleSheet,y(e.contentWindow.CSSStyleSheet.prototype),C(document.body),d.loaded=!0;for(var r=document.createDocumentFragment(),i=0,a=t.length;i<a;i++)t[i].disabled=!1,r.appendChild(t[i]);u.body.appendChild(r);for(var s=0,l=o.length;s<l;s++)r.appendChild(o[s]);document.body.insertBefore(r,document.body.firstChild);for(var c=0,m=n.length;c<m;c++)_(n[c]);n.length=0,t.length=0,o.length=0}}()},function(e,t,o){"use strict";function n(e){return e=e||[],Array.isArray(e)?e:[e]}function r(e){return"[Vaadin.Router] "+e}o.r(t);const i=["module","nomodule"];function a(e){if(!e.match(/.+\.[m]?js$/))throw new Error(r(`Unsupported type for bundle "${e}": .js or .mjs expected.`))}function s(e){if(!e||!p(e.path))throw new Error(r('Expected route config to be an object with a "path" string property, or an array of such objects'));const t=e.bundle,o=["component","redirect","bundle"];if(!(m(e.action)||Array.isArray(e.children)||m(e.children)||u(t)||o.some(t=>p(e[t]))))throw new Error(r(`Expected route config "${e.path}" to include either "${o.join('", "')}" or "action" function but none found.`));if(t)if(p(t))a(t);else{if(!i.some(e=>e in t))throw new Error(r('Expected route bundle to include either "nomodule" or "module" keys, or both'));i.forEach(e=>e in t&&a(t[e]))}e.redirect&&["bundle","component"].forEach(t=>{t in e&&console.warn(r(`Route config "${e.path}" has both "redirect" and "${t}" properties, and "redirect" will always override the latter. Did you mean to only use "${t}"?`))})}function l(e){n(e).forEach(e=>s(e))}function c(e,t){let o=document.head.querySelector('script[src="'+e+'"][async]');return o||(o=document.createElement("script"),o.setAttribute("src",e),"module"===t?o.setAttribute("type","module"):"nomodule"===t&&o.setAttribute("nomodule",""),o.async=!0),new Promise((e,t)=>{o.onreadystatechange=o.onload=t=>{o.__dynamicImportLoaded=!0,e(t)},o.onerror=e=>{o.parentNode&&o.parentNode.removeChild(o),t(e)},null===o.parentNode?document.head.appendChild(o):o.__dynamicImportLoaded&&e()})}function d(e,t){return!window.dispatchEvent(new CustomEvent("vaadin-router-"+e,{cancelable:"go"===e,detail:t}))}function u(e){return"object"==typeof e&&!!e}function m(e){return"function"==typeof e}function p(e){return"string"==typeof e}function h(e){const t=new Error(r(`Page not found (${e.pathname})`));return t.context=e,t.code=404,t}const g=new class{};function f(e){if(e.defaultPrevented)return;if(0!==e.button)return;if(e.shiftKey||e.ctrlKey||e.altKey||e.metaKey)return;let t=e.target;const o=e.composedPath?e.composedPath():e.path||[];for(let e=0;e<o.length;e++){const n=o[e];if(n.nodeName&&"a"===n.nodeName.toLowerCase()){t=n;break}}for(;t&&"a"!==t.nodeName.toLowerCase();)t=t.parentNode;if(!t||"a"!==t.nodeName.toLowerCase())return;if(t.target&&"_self"!==t.target.toLowerCase())return;if(t.hasAttribute("download"))return;if(t.hasAttribute("router-ignore"))return;if(t.pathname===window.location.pathname&&""!==t.hash)return;if((t.origin||function(e){const t=e.port,o=e.protocol;return`${o}//${"http:"===o&&"80"===t||"https:"===o&&"443"===t?e.hostname:e.host}`}(t))!==window.location.origin)return;const{pathname:n,search:r,hash:i}=t;d("go",{pathname:n,search:r,hash:i})&&e.preventDefault()}const v={activate(){window.document.addEventListener("click",f)},inactivate(){window.document.removeEventListener("click",f)}};function x(e){if("vaadin-router-ignore"===e.state)return;const{pathname:t,search:o,hash:n}=window.location;d("go",{pathname:t,search:o,hash:n})}/Trident/.test(navigator.userAgent)&&!m(window.PopStateEvent)&&(window.PopStateEvent=function(e,t){t=t||{};var o=document.createEvent("Event");return o.initEvent(e,Boolean(t.bubbles),Boolean(t.cancelable)),o.state=t.state||null,o},window.PopStateEvent.prototype=window.Event.prototype);const y={activate(){window.addEventListener("popstate",x)},inactivate(){window.removeEventListener("popstate",x)}};var b=A,w=T,_=function(e,t){return k(T(e,t))},S=k,C=N,E=new RegExp(["(\\\\.)","(?:\\:(\\w+)(?:\\(((?:\\\\.|[^\\\\()])+)\\))?|\\(((?:\\\\.|[^\\\\()])+)\\))([+*?])?"].join("|"),"g");function T(e,t){for(var o,n=[],r=0,i=0,a="",s=t&&t.delimiter||"/",l=t&&t.delimiters||"./",c=!1;null!==(o=E.exec(e));){var d=o[0],u=o[1],m=o.index;if(a+=e.slice(i,m),i=m+d.length,u)a+=u[1],c=!0;else{var p="",h=e[i],g=o[2],f=o[3],v=o[4],x=o[5];if(!c&&a.length){var y=a.length-1;l.indexOf(a[y])>-1&&(p=a[y],a=a.slice(0,y))}a&&(n.push(a),a="",c=!1);var b=""!==p&&void 0!==h&&h!==p,w="+"===x||"*"===x,_="?"===x||"*"===x,S=p||s,C=f||v;n.push({name:g||r++,prefix:p,delimiter:S,optional:_,repeat:w,partial:b,pattern:C?z(C):"[^"+O(S)+"]+?"})}}return(a||i<e.length)&&n.push(a+e.substr(i)),n}function k(e){for(var t=new Array(e.length),o=0;o<e.length;o++)"object"==typeof e[o]&&(t[o]=new RegExp("^(?:"+e[o].pattern+")$"));return function(o,n){for(var r="",i=n&&n.encode||encodeURIComponent,a=0;a<e.length;a++){var s=e[a];if("string"!=typeof s){var l,c=o?o[s.name]:void 0;if(Array.isArray(c)){if(!s.repeat)throw new TypeError('Expected "'+s.name+'" to not repeat, but got array');if(0===c.length){if(s.optional)continue;throw new TypeError('Expected "'+s.name+'" to not be empty')}for(var d=0;d<c.length;d++){if(l=i(c[d],s),!t[a].test(l))throw new TypeError('Expected all "'+s.name+'" to match "'+s.pattern+'"');r+=(0===d?s.prefix:s.delimiter)+l}}else if("string"!=typeof c&&"number"!=typeof c&&"boolean"!=typeof c){if(!s.optional)throw new TypeError('Expected "'+s.name+'" to be '+(s.repeat?"an array":"a string"));s.partial&&(r+=s.prefix)}else{if(l=i(String(c),s),!t[a].test(l))throw new TypeError('Expected "'+s.name+'" to match "'+s.pattern+'", but got "'+l+'"');r+=s.prefix+l}}else r+=s}return r}}function O(e){return e.replace(/([.+*?=^!:${}()[\]|/\\])/g,"\\$1")}function z(e){return e.replace(/([=!:$/()])/g,"\\$1")}function P(e){return e&&e.sensitive?"":"i"}function N(e,t,o){for(var n=(o=o||{}).strict,r=!1!==o.start,i=!1!==o.end,a=O(o.delimiter||"/"),s=o.delimiters||"./",l=[].concat(o.endsWith||[]).map(O).concat("$").join("|"),c=r?"^":"",d=0===e.length,u=0;u<e.length;u++){var m=e[u];if("string"==typeof m)c+=O(m),d=u===e.length-1&&s.indexOf(m[m.length-1])>-1;else{var p=m.repeat?"(?:"+m.pattern+")(?:"+O(m.delimiter)+"(?:"+m.pattern+"))*":m.pattern;t&&t.push(m),m.optional?m.partial?c+=O(m.prefix)+"("+p+")?":c+="(?:"+O(m.prefix)+"("+p+"))?":c+=O(m.prefix)+"("+p+")"}}return i?(n||(c+="(?:"+a+")?"),c+="$"===l?"$":"(?="+l+")"):(n||(c+="(?:"+a+"(?="+l+"))?"),d||(c+="(?="+a+"|"+l+")")),new RegExp(c,P(o))}function A(e,t,o){return e instanceof RegExp?function(e,t){if(!t)return e;var o=e.source.match(/\((?!\?)/g);if(o)for(var n=0;n<o.length;n++)t.push({name:n,prefix:null,delimiter:null,optional:!1,repeat:!1,partial:!1,pattern:null});return e}(e,t):Array.isArray(e)?function(e,t,o){for(var n=[],r=0;r<e.length;r++)n.push(A(e[r],t,o).source);return new RegExp("(?:"+n.join("|")+")",P(o))}(e,t,o):function(e,t,o){return N(T(e,o),t,o)}(e,t,o)}b.parse=w,b.compile=_,b.tokensToFunction=S,b.tokensToRegExp=C;const{hasOwnProperty:L}=Object.prototype,R=new Map;function j(e){try{return decodeURIComponent(e)}catch(t){return e}}function I(e,t,o,n,r){let i,a,s=0,l=e.path||"";return"/"===l.charAt(0)&&(o&&(l=l.substr(1)),o=!0),{next(c){if(e===c)return{done:!0};const d=e.__children=e.__children||e.children;if(!i&&(i=function(e,t,o,n,r){const i=`${e}|${o=!!o}`;let a=R.get(i);if(!a){const t=[];a={keys:t,pattern:b(e,t,{end:o,strict:""===e})},R.set(i,a)}const s=a.pattern.exec(t);if(!s)return null;const l=Object.assign({},r);for(let e=1;e<s.length;e++){const t=a.keys[e-1],o=t.name,n=s[e];void 0===n&&L.call(l,o)||(t.repeat?l[o]=n?n.split(t.delimiter).map(j):[]:l[o]=n?j(n):n)}return{path:s[0],keys:(n||[]).concat(a.keys),params:l}}(l,t,!d,n,r),i))return{done:!1,value:{route:e,keys:i.keys,params:i.params,path:i.path}};if(i&&d)for(;s<d.length;){if(!a){const n=d[s];n.parent=e;let r=i.path.length;r>0&&"/"===t.charAt(r)&&(r+=1),a=I(n,t.substr(r),o,i.keys,i.params)}const n=a.next(c);if(!n.done)return{done:!1,value:n.value};a=null,s++}return{done:!0}}}}function M(e){if(m(e.route.action))return e.route.action(e)}R.set("|false",{keys:[],pattern:/(?:)/});class V{constructor(e,t={}){if(Object(e)!==e)throw new TypeError("Invalid routes");this.baseUrl=t.baseUrl||"",this.errorHandler=t.errorHandler,this.resolveRoute=t.resolveRoute||M,this.context=Object.assign({resolver:this},t.context),this.root=Array.isArray(e)?{path:"",__children:e,parent:null,__synthetic:!0}:e,this.root.parent=null}getRoutes(){return[...this.root.__children]}setRoutes(e){l(e);const t=[...n(e)];this.root.__children=t}addRoutes(e){return l(e),this.root.__children.push(...n(e)),this.getRoutes()}removeRoutes(){this.setRoutes([])}resolve(e){const t=Object.assign({},this.context,p(e)?{pathname:e}:e),o=I(this.root,this.__normalizePathname(t.pathname),this.baseUrl),n=this.resolveRoute;let r=null,i=null,a=t;function s(e,l=r.value.route,c){const d=null===c&&r.value.route;return r=i||o.next(d),i=null,e||!r.done&&function(e,t){let o=t;for(;o;)if(o=o.parent,o===e)return!0;return!1}(l,r.value.route)?r.done?Promise.reject(h(t)):(a=Object.assign(a?{chain:a.chain?a.chain.slice(0):[]}:{},t,r.value),function(e,t){const{route:o,path:n}=t;if(o&&!o.__synthetic){const t={path:n,route:o};if(e.chain){if(o.parent){let t=e.chain.length;for(;t--&&e.chain[t].route&&e.chain[t].route!==o.parent;)e.chain.pop()}}else e.chain=[];e.chain.push(t)}}(a,r.value),Promise.resolve(n(a)).then(t=>null!=t&&t!==g?(a.result=t.result||t,a):s(e,l,t))):(i=r,Promise.resolve(g))}return t.next=s,Promise.resolve().then(()=>s(!0,this.root)).catch(e=>{const t=function(e){let t=`Path '${e.pathname}' is not properly resolved due to an error.`;const o=(e.route||{}).path;return o&&(t+=` Resolution had failed on route: '${o}'`),t}(a);if(e?console.warn(t):e=new Error(t),e.context=e.context||a,e instanceof DOMException||(e.code=e.code||500),this.errorHandler)return a.result=this.errorHandler(e),a;throw e})}static __createUrl(e,t){return new URL(e,t)}get __effectiveBaseUrl(){return this.baseUrl?this.constructor.__createUrl(this.baseUrl,document.baseURI||document.URL).href.replace(/[^\/]*$/,""):""}__normalizePathname(e){if(!this.baseUrl)return e;const t=this.__effectiveBaseUrl,o=this.constructor.__createUrl(e,t).href;return o.slice(0,t.length)===t?o.slice(t.length):void 0}}V.pathToRegexp=b;const{pathToRegexp:B}=V,F=new Map;function U(e,t){const o=e.get(t);if(o&&o.length>1)throw new Error(`Duplicate route with name "${t}". Try seting unique 'name' route properties.`);return o&&o[0]}function $(e){let t=e.path;return t=Array.isArray(t)?t[0]:t,void 0!==t?t:""}function D(e,t={}){if(!(e instanceof V))throw new TypeError("An instance of Resolver is expected");const o=new Map;return(n,r)=>{let i=U(o,n);if(!i&&(o.clear(),function e(t,o,n){const r=o.name||o.component;if(r&&(t.has(r)?t.get(r).push(o):t.set(r,[o])),Array.isArray(n))for(let r=0;r<n.length;r++){const i=n[r];i.parent=o,e(t,i,i.__children||i.children)}}(o,e.root,e.root.__children),i=U(o,n),!i))throw new Error(`Route "${n}" not found`);let a=F.get(i.fullPath);if(!a){let e=$(i),t=i.parent;for(;t;){const o=$(t);o&&(e=o.replace(/\/$/,"")+"/"+e.replace(/^\//,"")),t=t.parent}const o=B.parse(e),n=B.tokensToFunction(o),r=Object.create(null);for(let e=0;e<o.length;e++)p(o[e])||(r[o[e].name]=!0);a={toPath:n,keys:r},F.set(e,a),i.fullPath=e}let s=a.toPath(r,t)||"/";if(t.stringifyQueryParams&&r){const e={},o=Object.keys(r);for(let t=0;t<o.length;t++){const n=o[t];a.keys[n]||(e[n]=r[n])}const n=t.stringifyQueryParams(e);n&&(s+="?"===n.charAt(0)?n:"?"+n)}return s}}let H=[];function W(e){H.forEach(e=>e.inactivate()),e.forEach(e=>e.activate()),H=e}function q(e,t){return e.classList.add(t),new Promise(o=>{if((e=>{const t=getComputedStyle(e).getPropertyValue("animation-name");return t&&"none"!==t})(e)){const n=e.getBoundingClientRect(),r=`height: ${n.bottom-n.top}px; width: ${n.right-n.left}px`;e.setAttribute("style","position: absolute; "+r),((e,t)=>{const o=()=>{e.removeEventListener("animationend",o),t()};e.addEventListener("animationend",o)})(e,()=>{e.classList.remove(t),e.removeAttribute("style"),o()})}else e.classList.remove(t),o()})}function G(e){return null!=e}function J({pathname:e="",search:t="",hash:o="",chain:n=[],params:r={},redirectFrom:i,resolver:a},s){const l=n.map(e=>e.route);return{baseUrl:a&&a.baseUrl||"",pathname:e,search:t,hash:o,routes:l,route:s||l.length&&l[l.length-1]||null,params:r,redirectFrom:i,getUrl:(e={})=>Z(te.pathToRegexp.compile(ee(l))(Object.assign({},r,e)),a)}}function K(e,t){const o=Object.assign({},e.params);return{redirect:{pathname:t,from:e.pathname,params:o}}}function Q(e,t,o){if(m(e))return e.apply(o,t)}function X(e,t,o){return n=>n&&(n.cancel||n.redirect)?n:o?Q(o[e],t,o):void 0}function Y(e){if(e&&e.length){const t=e[0].parentNode;for(let o=0;o<e.length;o++)t.removeChild(e[o])}}function Z(e,t){const o=t.__effectiveBaseUrl;return o?t.constructor.__createUrl(e.replace(/^\//,""),o).pathname:e}function ee(e){return e.map(e=>e.path).reduce((e,t)=>t.length?e.replace(/\/$/,"")+"/"+t.replace(/^\//,""):e,"")}class te extends V{constructor(e,t){const o=document.head.querySelector("base"),n=o&&o.getAttribute("href");super([],Object.assign({baseUrl:n&&V.__createUrl(n,document.URL).pathname.replace(/[^\/]*$/,"")},t)),this.resolveRoute=e=>this.__resolveRoute(e);const r=te.NavigationTrigger;te.setTriggers.apply(te,Object.keys(r).map(e=>r[e])),this.baseUrl,this.ready,this.ready=Promise.resolve(e),this.location,this.location=J({resolver:this}),this.__lastStartedRenderId=0,this.__navigationEventHandler=this.__onNavigationEvent.bind(this),this.setOutlet(e),this.subscribe(),this.__createdByRouter=new WeakMap,this.__addedByRouter=new WeakMap}__resolveRoute(e){const t=e.route;let o=Promise.resolve();m(t.children)&&(o=o.then(()=>t.children(function(e){const t=Object.assign({},e);return delete t.next,t}(e))).then(e=>{G(e)||m(t.children)||(e=t.children),function(e,t){if(!Array.isArray(e)&&!u(e))throw new Error(r(`Incorrect "children" value for the route ${t.path}: expected array or object, but got ${e}`));t.__children=[];const o=n(e);for(let e=0;e<o.length;e++)s(o[e]),t.__children.push(o[e])}(e,t)}));const a={redirect:t=>K(e,t),component:e=>{const t=document.createElement(e);return this.__createdByRouter.set(t,!0),t}};return o.then(()=>{if(this.__isLatestRender(e))return Q(t.action,[e,a],t)}).then(e=>{return G(e)&&(e instanceof HTMLElement||e.redirect||e===g)?e:p(t.redirect)?a.redirect(t.redirect):t.bundle?(o=t.bundle,p(o)?c(o):Promise.race(i.filter(e=>e in o).map(e=>c(o[e],e)))).then(()=>{},()=>{throw new Error(r(`Bundle not found: ${t.bundle}. Check if the file name is correct`))}):void 0;var o}).then(e=>G(e)?e:p(t.component)?a.component(t.component):void 0)}setOutlet(e){e&&this.__ensureOutlet(e),this.__outlet=e}getOutlet(){return this.__outlet}setRoutes(e,t=!1){return this.__previousContext=void 0,this.__urlForName=void 0,super.setRoutes(e),t||this.__onNavigationEvent(),this.ready}render(e,t){const o=++this.__lastStartedRenderId,n=Object.assign({search:"",hash:""},p(e)?{pathname:e}:e,{__renderId:o});return this.ready=this.resolve(n).then(e=>this.__fullyResolveChain(e)).then(e=>{if(this.__isLatestRender(e)){const n=this.__previousContext;if(e===n)return this.__updateBrowserHistory(n,!0),this.location;if(this.location=J(e),t&&this.__updateBrowserHistory(e,1===o),d("location-changed",{router:this,location:this.location}),e.__skipAttach)return this.__copyUnchangedElements(e,n),this.__previousContext=e,this.location;this.__addAppearingContent(e,n);const r=this.__animateIfNeeded(e);return this.__runOnAfterEnterCallbacks(e),this.__runOnAfterLeaveCallbacks(e,n),r.then(()=>{if(this.__isLatestRender(e))return this.__removeDisappearingContent(),this.__previousContext=e,this.location})}}).catch(e=>{if(o===this.__lastStartedRenderId)throw t&&this.__updateBrowserHistory(n),Y(this.__outlet&&this.__outlet.children),this.location=J(Object.assign(n,{resolver:this})),d("error",Object.assign({router:this,error:e},n)),e}),this.ready}__fullyResolveChain(e,t=e){return this.__findComponentContextAfterAllRedirects(t).then(o=>{const n=o!==t?o:e,r=Z(ee(o.chain),o.resolver)===o.pathname,i=(e,t=e.route,o)=>e.next(void 0,t,o).then(o=>null===o||o===g?r?e:null!==t.parent?i(e,t.parent,o):o:o);return i(o).then(e=>{if(null===e||e===g)throw h(n);return e&&e!==g&&e!==o?this.__fullyResolveChain(n,e):this.__amendWithOnBeforeCallbacks(o)})})}__findComponentContextAfterAllRedirects(e){const t=e.result;return t instanceof HTMLElement?(function(e,t){t.location=J(e);const o=e.chain.map(e=>e.route).indexOf(e.route);e.chain[o].element=t}(e,t),Promise.resolve(e)):t.redirect?this.__redirect(t.redirect,e.__redirectCount,e.__renderId).then(e=>this.__findComponentContextAfterAllRedirects(e)):t instanceof Error?Promise.reject(t):Promise.reject(new Error(r(`Invalid route resolution result for path "${e.pathname}". Expected redirect object or HTML element, but got: "${function(e){if("object"!=typeof e)return String(e);const t=Object.prototype.toString.call(e).match(/ (.*)\]$/)[1];return"Object"===t||"Array"===t?`${t} ${JSON.stringify(e)}`:t}(t)}". Double check the action return value for the route.`)))}__amendWithOnBeforeCallbacks(e){return this.__runOnBeforeCallbacks(e).then(t=>t===this.__previousContext||t===e?t:this.__fullyResolveChain(t))}__runOnBeforeCallbacks(e){const t=this.__previousContext||{},o=t.chain||[],n=e.chain;let r=Promise.resolve();const i=()=>({cancel:!0}),a=t=>K(e,t);if(e.__divergedChainIndex=0,e.__skipAttach=!1,o.length){for(let t=0;t<Math.min(o.length,n.length)&&(o[t].route===n[t].route&&(o[t].path===n[t].path||o[t].element===n[t].element)&&this.__isReusableElement(o[t].element,n[t].element));t=++e.__divergedChainIndex);if(e.__skipAttach=n.length===o.length&&e.__divergedChainIndex==n.length&&this.__isReusableElement(e.result,t.result),e.__skipAttach){for(let t=n.length-1;t>=0;t--)r=this.__runOnBeforeLeaveCallbacks(r,e,{prevent:i},o[t]);for(let t=0;t<n.length;t++)r=this.__runOnBeforeEnterCallbacks(r,e,{prevent:i,redirect:a},n[t]),o[t].element.location=J(e,o[t].route)}else for(let t=o.length-1;t>=e.__divergedChainIndex;t--)r=this.__runOnBeforeLeaveCallbacks(r,e,{prevent:i},o[t])}if(!e.__skipAttach)for(let t=0;t<n.length;t++)t<e.__divergedChainIndex?t<o.length&&o[t].element&&(o[t].element.location=J(e,o[t].route)):(r=this.__runOnBeforeEnterCallbacks(r,e,{prevent:i,redirect:a},n[t]),n[t].element&&(n[t].element.location=J(e,n[t].route)));return r.then(t=>{if(t){if(t.cancel)return this.__previousContext.__renderId=e.__renderId,this.__previousContext;if(t.redirect)return this.__redirect(t.redirect,e.__redirectCount,e.__renderId)}return e})}__runOnBeforeLeaveCallbacks(e,t,o,n){const r=J(t);return e.then(e=>{if(this.__isLatestRender(t)){return X("onBeforeLeave",[r,o,this],n.element)(e)}}).then(e=>{if(!(e||{}).redirect)return e})}__runOnBeforeEnterCallbacks(e,t,o,n){const r=J(t,n.route);return e.then(e=>{if(this.__isLatestRender(t)){return X("onBeforeEnter",[r,o,this],n.element)(e)}})}__isReusableElement(e,t){return!(!e||!t)&&(this.__createdByRouter.get(e)&&this.__createdByRouter.get(t)?e.localName===t.localName:e===t)}__isLatestRender(e){return e.__renderId===this.__lastStartedRenderId}__redirect(e,t,o){if(t>256)throw new Error(r("Too many redirects when rendering "+e.from));return this.resolve({pathname:this.urlForPath(e.pathname,e.params),redirectFrom:e.from,__redirectCount:(t||0)+1,__renderId:o})}__ensureOutlet(e=this.__outlet){if(!(e instanceof Node))throw new TypeError(r(`Expected router outlet to be a valid DOM Node (but got ${e})`))}__updateBrowserHistory({pathname:e,search:t="",hash:o=""},n){if(window.location.pathname!==e||window.location.search!==t||window.location.hash!==o){const r=n?"replaceState":"pushState";window.history[r](null,document.title,e+t+o),window.dispatchEvent(new PopStateEvent("popstate",{state:"vaadin-router-ignore"}))}}__copyUnchangedElements(e,t){let o=this.__outlet;for(let n=0;n<e.__divergedChainIndex;n++){const r=t&&t.chain[n].element;if(r){if(r.parentNode!==o)break;e.chain[n].element=r,o=r}}return o}__addAppearingContent(e,t){this.__ensureOutlet(),this.__removeAppearingContent();const o=this.__copyUnchangedElements(e,t);this.__appearingContent=[],this.__disappearingContent=Array.from(o.children).filter(t=>this.__addedByRouter.get(t)&&t!==e.result);let n=o;for(let t=e.__divergedChainIndex;t<e.chain.length;t++){const r=e.chain[t].element;r&&(n.appendChild(r),this.__addedByRouter.set(r,!0),n===o&&this.__appearingContent.push(r),n=r)}}__removeDisappearingContent(){this.__disappearingContent&&Y(this.__disappearingContent),this.__disappearingContent=null,this.__appearingContent=null}__removeAppearingContent(){this.__disappearingContent&&this.__appearingContent&&(Y(this.__appearingContent),this.__disappearingContent=null,this.__appearingContent=null)}__runOnAfterLeaveCallbacks(e,t){if(t)for(let o=t.chain.length-1;o>=e.__divergedChainIndex&&this.__isLatestRender(e);o--){const n=t.chain[o].element;if(n)try{const o=J(e);Q(n.onAfterLeave,[o,{},t.resolver],n)}finally{this.__disappearingContent.indexOf(n)>-1&&Y(n.children)}}}__runOnAfterEnterCallbacks(e){for(let t=e.__divergedChainIndex;t<e.chain.length&&this.__isLatestRender(e);t++){const o=e.chain[t].element||{},n=J(e,e.chain[t].route);Q(o.onAfterEnter,[n,{},e.resolver],o)}}__animateIfNeeded(e){const t=(this.__disappearingContent||[])[0],o=(this.__appearingContent||[])[0],n=[],r=e.chain;let i;for(let e=r.length;e>0;e--)if(r[e-1].route.animate){i=r[e-1].route.animate;break}if(t&&o&&i){const e=u(i)&&i.leave||"leaving",r=u(i)&&i.enter||"entering";n.push(q(t,e)),n.push(q(o,r))}return Promise.all(n).then(()=>e)}subscribe(){window.addEventListener("vaadin-router-go",this.__navigationEventHandler)}unsubscribe(){window.removeEventListener("vaadin-router-go",this.__navigationEventHandler)}__onNavigationEvent(e){const{pathname:t,search:o,hash:n}=e?e.detail:window.location;p(this.__normalizePathname(t))&&(e&&e.preventDefault&&e.preventDefault(),this.render({pathname:t,search:o,hash:n},!0))}static setTriggers(...e){W(e)}urlForName(e,t){return this.__urlForName||(this.__urlForName=D(this)),Z(this.__urlForName(e,t),this)}urlForPath(e,t){return Z(te.pathToRegexp.compile(e)(t),this)}static go(e){const{pathname:t,search:o,hash:n}=p(e)?this.__createUrl(e,"http://a"):e;return d("go",{pathname:t,search:o,hash:n})}}const oe=/\/\*\*\s+vaadin-dev-mode:start([\s\S]*)vaadin-dev-mode:end\s+\*\*\//i,ne=window.Vaadin&&window.Vaadin.Flow&&window.Vaadin.Flow.clients;function re(e,t){if("function"!=typeof e)return;const o=oe.exec(e.toString());if(o)try{e=new Function(o[1])}catch(e){console.log("vaadin-development-mode-detector: uncommentAndRun() failed",e)}return e(t)}window.Vaadin=window.Vaadin||{};const ie=function(e,t){if(window.Vaadin.developmentMode)return re(e,t)};function ae(){}void 0===window.Vaadin.developmentMode&&(window.Vaadin.developmentMode=function(){try{return!!localStorage.getItem("vaadin.developmentmode.force")||["localhost","127.0.0.1"].indexOf(window.location.hostname)>=0&&(ne?!function(){if(ne){if(Object.keys(ne).map(e=>ne[e]).filter(e=>e.productionMode).length>0)return!0}return!1}():!re((function(){return!0})))}catch(e){return!1}}());window.Vaadin=window.Vaadin||{},window.Vaadin.registrations=window.Vaadin.registrations||[],window.Vaadin.registrations.push({is:"@vaadin/router",version:"1.7.2"}),ie(ae),te.NavigationTrigger={POPSTATE:y,CLICK:v};var se=o(24),le=o(7),ce=function(e,t,o,n){return new(o||(o=Promise))((function(r,i){function a(e){try{l(n.next(e))}catch(e){i(e)}}function s(e){try{l(n.throw(e))}catch(e){i(e)}}function l(e){var t;e.done?r(e.value):(t=e.value,t instanceof o?t:new o((function(e){e(t)}))).then(a,s)}l((n=n.apply(e,t||[])).next())}))};const de=window.document.body,ue=window;class me extends Error{constructor(e){super(e)}}const{serverSideRoutes:pe}=new class{constructor(e){this.response=void 0,this.pathname="",this.isActive=!1,this.baseRegex=/^\//,de.$=de.$||[],this.config=e||{},ue.Vaadin=ue.Vaadin||{},ue.Vaadin.Flow=ue.Vaadin.Flow||{},ue.Vaadin.Flow.clients={TypeScript:{isActive:()=>this.isActive}};const t=document.head.querySelector("base");this.baseRegex=new RegExp("^"+(document.baseURI||t&&t.href||"/").replace(/^https?:\/\/[^/]+/i,"")),this.appShellTitle=document.title,this.addConnectionIndicator()}get serverSideRoutes(){return[{path:"(.*)",action:this.action}]}loadingStarted(){this.isActive=!0,ue.Vaadin.connectionState.loadingStarted()}loadingFinished(){this.isActive=!1,ue.Vaadin.connectionState.loadingFinished()}get action(){return e=>ce(this,void 0,void 0,(function*(){if(this.pathname=e.pathname,!ue.Vaadin.connectionState.online)return this.offlineStubAction();try{yield this.flowInit()}catch(e){if(e instanceof me)return ue.Vaadin.connectionState.state=le.a.CONNECTION_LOST,this.offlineStubAction();throw e}return this.container.onBeforeEnter=(e,t)=>this.flowNavigate(e,t),this.container.onBeforeLeave=(e,t)=>this.flowLeave(e,t),this.container}))}flowLeave(e,t){return ce(this,void 0,void 0,(function*(){const o=ue.Vaadin.connectionState;return this.pathname===e.pathname||!this.isFlowClientLoaded()||o.offline?Promise.resolve({}):new Promise(o=>{this.loadingStarted(),this.container.serverConnected=e=>{o(t&&e?t.prevent():{}),this.loadingFinished()},de.$server.leaveNavigation(this.getFlowRoute(e))})}))}flowNavigate(e,t){return ce(this,void 0,void 0,(function*(){return this.response?new Promise(o=>{this.loadingStarted(),this.container.serverConnected=(e,n)=>{t&&e?o(t.prevent()):t&&t.redirect&&n?o(t.redirect(n.pathname)):(this.container.style.display="",o(this.container)),this.loadingFinished()},de.$server.connectClient(this.container.localName,this.container.id,this.getFlowRoute(e),this.appShellTitle)}):Promise.resolve(this.container)}))}getFlowRoute(e){return(e.pathname+(e.search||"")).replace(this.baseRegex,"")}flowInit(e=!1){return ce(this,void 0,void 0,(function*(){if(!this.isFlowClientLoaded()){this.loadingStarted(),this.response=yield this.flowInitUi(e),this.response.appConfig.clientRouting=!e;const{pushScript:t,appConfig:n}=this.response;"string"==typeof t&&(yield this.loadScript(t));const{appId:r}=n,i=yield o.e(4).then(o.bind(null,265));yield i.init(this.response),"function"==typeof this.config.imports&&(this.injectAppIdScript(r),yield this.config.imports());const a=yield o.e(5).then(o.bind(null,266));if(yield this.flowInitClient(a),!e){const e="flow-container-"+r.toLowerCase();this.container=de.$[r]=document.createElement(e),this.container.id=r,this.container.style.display="none",document.body.appendChild(this.container)}this.loadingFinished()}return this.response}))}loadScript(e){return ce(this,void 0,void 0,(function*(){return new Promise((t,o)=>{const n=document.createElement("script");n.onload=()=>t(),n.onerror=o,n.src=e,document.body.appendChild(n)})}))}injectAppIdScript(e){const t=e.substring(0,e.lastIndexOf("-")),o=document.createElement("script");o.type="module",o.setAttribute("data-app-id",t),document.body.append(o)}flowInitClient(e){return ce(this,void 0,void 0,(function*(){return e.init(),new Promise(e=>{const t=setInterval(()=>{Object.keys(ue.Vaadin.Flow.clients).filter(e=>"TypeScript"!==e).reduce((e,t)=>e||ue.Vaadin.Flow.clients[t].isActive(),!1)||(clearInterval(t),e())},5)})}))}flowInitUi(e){return ce(this,void 0,void 0,(function*(){const t=ue.Vaadin&&ue.Vaadin.TypeScript&&ue.Vaadin.TypeScript.initial;return t?(ue.Vaadin.TypeScript.initial=void 0,Promise.resolve(t)):new Promise((t,o)=>{const n=new XMLHttpRequest,r=e?"&serverSideRouting":"",i="?v-r=init&location="+encodeURIComponent(this.getFlowRoute(location))+r;n.open("GET",i),n.onerror=()=>o(new me(`Invalid server response when initializing Flow UI.\n        ${n.status}\n        ${n.responseText}`)),n.onload=()=>{const e=n.getResponseHeader("content-type");e&&-1!==e.indexOf("application/json")?t(JSON.parse(n.responseText)):n.onerror()},n.send()})}))}addConnectionIndicator(){se.a.create(),ue.addEventListener("online",()=>{if(!this.isFlowClientLoaded()){ue.Vaadin.connectionState.state=le.a.RECONNECTING;const e=new XMLHttpRequest;e.open("HEAD","sw.js"),e.onload=()=>{ue.Vaadin.connectionState.state=le.a.CONNECTED},e.onerror=()=>{ue.Vaadin.connectionState.state=le.a.CONNECTION_LOST},e.send()}}),ue.addEventListener("offline",()=>{this.isFlowClientLoaded()||(ue.Vaadin.connectionState.state=le.a.CONNECTION_LOST)})}offlineStubAction(){return ce(this,void 0,void 0,(function*(){const e=document.createElement("iframe");let t;e.setAttribute("src","./offline-stub.html"),e.setAttribute("style","width: 100%; height: 100%; border: 0"),this.response=void 0;const o=()=>{void 0!==t&&(ue.Vaadin.connectionState.removeStateChangeListener(t),t=void 0)};return e.onBeforeEnter=(e,n,r)=>{t=()=>{ue.Vaadin.connectionState.online&&(o(),r.render(e,!1))},ue.Vaadin.connectionState.addStateChangeListener(t)},e.onBeforeLeave=(e,t,n)=>{o()},e}))}isFlowClientLoaded(){return void 0!==this.response}}({imports:()=>Promise.all([o.e(2),o.e(6)]).then(o.bind(null,267))}),he=[...pe];new te(document.querySelector("#outlet")).setRoutes(he);o(29);var ge=o(14),fe=o(18),ve=(o(28),o(1).c`#header {
  height: var(--lumo-size-xl);
  box-shadow: var(--lumo-box-shadow-s);
}
#header vaadin-avatar {
  margin-left: auto;
  margin-right: var(--lumo-space-m);
}
vaadin-app-layout[dir='rtl'] #header vaadin-avatar {
  margin-left: var(--lumo-space-m);
  margin-right: auto;
}
#header h1 {
  font-size: var(--lumo-font-size-l);
  margin: 0;
}
#logo {
  box-sizing: border-box;
  box-shadow: inset 0 -1px var(--lumo-contrast-10pct);
  padding: var(--lumo-space-s) var(--lumo-space-m);
}
#logo img {
  height: calc(var(--lumo-size-l) * 1.5);
}
#logo h1 {
  font-size: var(--lumo-font-size-xl);
  font-weight: 600;
  margin: 0 var(--lumo-space-s);
}

vaadin-tab {
  font-size: var(--lumo-font-size-s);
  height: var(--lumo-size-l);
  font-weight: 600;
  color: var(--lumo-body-text-color);
}

vaadin-tab:hover {
  background-color: var(--lumo-contrast-5pct);
}

vaadin-tab[selected] {
  background-color: var(--lumo-primary-color-10pct);
  color: var(--lumo-primary-text-color);
}
.generator-view {
  display: block;
}
.about-view {
  display: block;
}
/* === Screen readers === */
.sr-only {
  border-width: 0;
  clip: rect(0, 0, 0, 0);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  white-space: nowrap;
  width: 1px;
}

/* === Background color === */
.bg-base {
  background-color: var(--lumo-base-color);
}

.bg-transparent {
  background-color: transparent;
}

.bg-contrast-5 {
  background-color: var(--lumo-contrast-5pct);
}
.bg-contrast-10 {
  background-color: var(--lumo-contrast-10pct);
}
.bg-contrast-20 {
  background-color: var(--lumo-contrast-20pct);
}
.bg-contrast-30 {
  background-color: var(--lumo-contrast-30pct);
}
.bg-contrast-40 {
  background-color: var(--lumo-contrast-40pct);
}
.bg-contrast-50 {
  background-color: var(--lumo-contrast-50pct);
}
.bg-contrast-60 {
  background-color: var(--lumo-contrast-60pct);
}
.bg-contrast-70 {
  background-color: var(--lumo-contrast-70pct);
}
.bg-contrast-80 {
  background-color: var(--lumo-contrast-80pct);
}
.bg-contrast-90 {
  background-color: var(--lumo-contrast-90pct);
}
.bg-contrast {
  background-color: var(--lumo-contrast);
}

.bg-primary {
  background-color: var(--lumo-primary-color);
}
.bg-primary-50 {
  background-color: var(--lumo-primary-color-50pct);
}
.bg-primary-10 {
  background-color: var(--lumo-primary-color-10pct);
}

.bg-error {
  background-color: var(--lumo-error-color);
}
.bg-error-50 {
  background-color: var(--lumo-error-color-50pct);
}
.bg-error-10 {
  background-color: var(--lumo-error-color-10pct);
}

.bg-success {
  background-color: var(--lumo-success-color);
}
.bg-success-50 {
  background-color: var(--lumo-success-color-50pct);
}
.bg-success-10 {
  background-color: var(--lumo-success-color-10pct);
}

/* === Border === */
.border-0 {
  border: none;
}
.border {
  border: 1px solid;
}
.border-b {
  border-bottom: 1px solid;
}
.border-l {
  border-left: 1px solid;
}
.border-r {
  border-right: 1px solid;
}
.border-t {
  border-top: 1px solid;
}

/* === Border color === */
.border-contrast-5 {
  border-color: var(--lumo-contrast-5pct);
}
.border-contrast-10 {
  border-color: var(--lumo-contrast-10pct);
}
.border-contrast-20 {
  border-color: var(--lumo-contrast-20pct);
}
.border-contrast-30 {
  border-color: var(--lumo-contrast-30pct);
}
.border-contrast-40 {
  border-color: var(--lumo-contrast-40pct);
}
.border-contrast-50 {
  border-color: var(--lumo-contrast-50pct);
}
.border-contrast-60 {
  border-color: var(--lumo-contrast-60pct);
}
.border-contrast-70 {
  border-color: var(--lumo-contrast-70pct);
}
.border-contrast-80 {
  border-color: var(--lumo-contrast-80pct);
}
.border-contrast-90 {
  border-color: var(--lumo-contrast-90pct);
}
.border-contrast {
  border-color: var(--lumo-contrast);
}

.border-primary {
  border-color: var(--lumo-primary-color);
}
.border-primary-50 {
  border-color: var(--lumo-primary-color-50pct);
}
.border-primary-10 {
  border-color: var(--lumo-primary-color-10pct);
}

.border-error {
  border-color: var(--lumo-error-color);
}
.border-error-50 {
  border-color: var(--lumo-error-color-50pct);
}
.border-error-10 {
  border-color: var(--lumo-error-color-10pct);
}

.border-success {
  border-color: var(--lumo-success-color);
}
.border-success-50 {
  border-color: var(--lumo-success-color-50pct);
}
.border-success-10 {
  border-color: var(--lumo-success-color-10pct);
}

/* === Border radius === */
.rounded-none {
  border-radius: 0;
}
.rounded-s {
  border-radius: var(--lumo-border-radius-s);
}
.rounded-m {
  border-radius: var(--lumo-border-radius-m);
}
.rounded-l {
  border-radius: var(--lumo-border-radius-l);
}

/* === Align content === */
.content-center {
  align-content: center;
}
.content-end {
  align-content: flex-end;
}
.content-start {
  align-content: flex-start;
}
.content-around {
  align-content: space-around;
}
.content-between {
  align-content: space-between;
}
.content-evenly {
  align-content: space-evenly;
}
.content-stretch {
  align-content: stretch;
}

/* === Align items === */
.items-baseline {
  align-items: baseline;
}
.items-center {
  align-items: center;
}
.items-end {
  align-items: flex-end;
}
.items-start {
  align-items: flex-start;
}
.items-stretch   {
  align-items: stretch;
}

/* === Align self === */
.self-auto {
  align-self: auto;
}
.self-baseline {
  align-self: baseline;
}
.self-center {
  align-self: center;
}
.self-end {
  align-self: flex-end;
}
.self-start {
  align-self: flex-start;
}
.self-stretch   {
  align-self: stretch;
}

/* === Justify content === */
.justify-center {
  justify-content: center;
}
.justify-end {
  justify-content: flex-end;
}
.justify-start {
  justify-content: flex-start;
}
.justify-around {
  justify-content: space-around;
}
.justify-between {
  justify-content: space-between;
}
.justify-evenly {
  justify-content: space-evenly;
}

/* === Box shadows === */
.shadow-xs {
  box-shadow: var(--lumo-box-shadow-xs);
}
.shadow-s {
  box-shadow: var(--lumo-box-shadow-s);
}
.shadow-m {
  box-shadow: var(--lumo-box-shadow-m);
}
.shadow-l {
  box-shadow: var(--lumo-box-shadow-l);
}
.shadow-xl {
  box-shadow: var(--lumo-box-shadow-xl);
}

/* === Flex === */
.flex-auto {
  flex: auto;
}
.flex-none {
  flex: none;
}

/* === Flex direction === */
.flex-col {
  flex-direction: column;
}
.flex-col-reverse {
  flex-direction: column-reverse;
}
.flex-row {
  flex-direction: row;
}
.flex-row-reverse {
  flex-direction: row-reverse;
}

/* === Flex grow === */
.flex-grow-0 {
  flex-grow: 0;
}
.flex-grow {
  flex-grow: 1;
}

/* === Flex shrink === */
.flex-shrink-0 {
  flex-shrink: 0;
}
.flex-shrink {
  flex-shrink: 1;
}

/* === Flex wrap === */
.flex-nowrap {
  flex-wrap: nowrap;
}
.flex-wrap {
  flex-wrap: wrap;
}
.flex-wrap-reverse {
  flex-wrap: wrap-reverse;
}

/* === Responsive design === */
@media (min-width: 640px) {
  .sm\\:flex-col {
    flex-direction: column;
  }
  .sm\\:flex-row {
    flex-direction: row;
  }
}

@media (min-width: 768px) {
  .md\\:flex-col {
    flex-direction: column;
  }
  .md\\:flex-row {
    flex-direction: row;
  }
}
@media (min-width: 1024px) {
  .lg\\:flex-col {
    flex-direction: column;
  }
  .lg\\:flex-row {
    flex-direction: row;
  }
}
@media (min-width: 1280px) {
  .xl\\:flex-col {
    flex-direction: column;
  }
  .xl\\:flex-row {
    flex-direction: row;
  }
}
@media (min-width: 1536px) {
  .\\32xl\\:flex-col {
    flex-direction: column;
  }
  .\\32xl\\:flex-row {
    flex-direction: row;
  }
}

/* === Gap === */
.gap-xs {
  gap: var(--lumo-space-xs);
}
.gap-s {
  gap: var(--lumo-space-s);
}
.gap-m {
  gap: var(--lumo-space-m);
}
.gap-l {
  gap: var(--lumo-space-l);
}
.gap-xl {
  gap: var(--lumo-space-xl);
}

/* === Gap (column) === */
.gap-x-xs {
  column-gap: var(--lumo-space-xs);
}
.gap-x-s {
  column-gap: var(--lumo-space-s);
}
.gap-x-m {
  column-gap: var(--lumo-space-m);
}
.gap-x-l {
  column-gap: var(--lumo-space-l);
}
.gap-x-xl {
  column-gap: var(--lumo-space-xl);
}

/* === Gap (row) === */
.gap-y-xs {
  row-gap: var(--lumo-space-xs);
}
.gap-y-s {
  row-gap: var(--lumo-space-s);
}
.gap-y-m {
  row-gap: var(--lumo-space-m);
}
.gap-y-l {
  row-gap: var(--lumo-space-l);
}
.gap-y-xl {
  row-gap: var(--lumo-space-xl);
}

/* === Grid auto flow === */
.grid-flow-col {
  grid-auto-flow: column;
}
.grid-flow-row {
  grid-auto-flow: row;
}

/* === Grid columns === */
.grid-cols-1 {
  grid-template-columns: repeat(1, minmax(0, 1fr));
}
.grid-cols-2 {
  grid-template-columns: repeat(2, minmax(0, 1fr));
}
.grid-cols-3 {
  grid-template-columns: repeat(3, minmax(0, 1fr));
}
.grid-cols-4 {
  grid-template-columns: repeat(4, minmax(0, 1fr));
}
.grid-cols-5 {
  grid-template-columns: repeat(5, minmax(0, 1fr));
}
.grid-cols-6 {
  grid-template-columns: repeat(6, minmax(0, 1fr));
}
.grid-cols-7 {
  grid-template-columns: repeat(7, minmax(0, 1fr));
}
.grid-cols-8 {
  grid-template-columns: repeat(8, minmax(0, 1fr));
}
.grid-cols-9 {
  grid-template-columns: repeat(9, minmax(0, 1fr));
}
.grid-cols-10 {
  grid-template-columns: repeat(10, minmax(0, 1fr));
}
.grid-cols-11 {
  grid-template-columns: repeat(11, minmax(0, 1fr));
}
.grid-cols-12 {
  grid-template-columns: repeat(12, minmax(0, 1fr));
}

/* === Grid rows === */
.grid-rows-1 {
  grid-template-rows: repeat(1, minmax(0, 1fr));
}
.grid-rows-2 {
  grid-template-rows: repeat(2, minmax(0, 1fr));
}
.grid-rows-3 {
  grid-template-rows: repeat(3, minmax(0, 1fr));
}
.grid-rows-4 {
  grid-template-rows: repeat(4, minmax(0, 1fr));
}
.grid-rows-5 {
  grid-template-rows: repeat(5, minmax(0, 1fr));
}
.grid-rows-6 {
  grid-template-rows: repeat(6, minmax(0, 1fr));
}

/* === Span (column) === */
.col-span-1 {
  grid-column: span 1 / span 1;
}
.col-span-2 {
  grid-column: span 2 / span 2;
}
.col-span-3 {
  grid-column: span 3 / span 3;
}
.col-span-4 {
  grid-column: span 4 / span 4;
}
.col-span-5 {
  grid-column: span 5 / span 5;
}
.col-span-6 {
  grid-column: span 6 / span 6;
}
.col-span-7 {
  grid-column: span 7 / span 7;
}
.col-span-8 {
  grid-column: span 8 / span 8;
}
.col-span-9 {
  grid-column: span 9 / span 9;
}
.col-span-10 {
  grid-column: span 10 / span 10;
}
.col-span-11 {
  grid-column: span 11 / span 11;
}
.col-span-12 {
  grid-column: span 12 / span 12;
}

/* === Span (row) === */
.row-span-1 {
  grid-row: span 1 / span 1;
}
.row-span-2 {
  grid-row: span 2 / span 2;
}
.row-span-3 {
  grid-row: span 3 / span 3;
}
.row-span-4 {
  grid-row: span 4 / span 4;
}
.row-span-5 {
  grid-row: span 5 / span 5;
}
.row-span-6 {
  grid-row: span 6 / span 6;
}

/* === Responsive design === */
@media (min-width: 640px) {
  .sm\\:grid-cols-1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .sm\\:grid-cols-2 {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  .sm\\:grid-cols-3 {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  .sm\\:grid-cols-4 {
    grid-template-columns: repeat(4, minmax(0, 1fr));
  }
  .sm\\:grid-cols-5 {
    grid-template-columns: repeat(5, minmax(0, 1fr));
  }
  .sm\\:grid-cols-6 {
    grid-template-columns: repeat(6, minmax(0, 1fr));
  }
  .sm\\:grid-cols-7 {
    grid-template-columns: repeat(7, minmax(0, 1fr));
  }
  .sm\\:grid-cols-8 {
    grid-template-columns: repeat(8, minmax(0, 1fr));
  }
  .sm\\:grid-cols-9 {
    grid-template-columns: repeat(9, minmax(0, 1fr));
  }
  .sm\\:grid-cols-10 {
    grid-template-columns: repeat(10, minmax(0, 1fr));
  }
  .sm\\:grid-cols-11 {
    grid-template-columns: repeat(11, minmax(0, 1fr));
  }
  .sm\\:grid-cols-12 {
    grid-template-columns: repeat(12, minmax(0, 1fr));
  }
}

@media (min-width: 768px) {
  .md\\:grid-cols-1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .md\\:grid-cols-2 {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  .md\\:grid-cols-3 {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  .md\\:grid-cols-4 {
    grid-template-columns: repeat(4, minmax(0, 1fr));
  }
  .md\\:grid-cols-5 {
    grid-template-columns: repeat(5, minmax(0, 1fr));
  }
  .md\\:grid-cols-6 {
    grid-template-columns: repeat(6, minmax(0, 1fr));
  }
  .md\\:grid-cols-7 {
    grid-template-columns: repeat(7, minmax(0, 1fr));
  }
  .md\\:grid-cols-8 {
    grid-template-columns: repeat(8, minmax(0, 1fr));
  }
  .md\\:grid-cols-9 {
    grid-template-columns: repeat(9, minmax(0, 1fr));
  }
  .md\\:grid-cols-10 {
    grid-template-columns: repeat(10, minmax(0, 1fr));
  }
  .md\\:grid-cols-11 {
    grid-template-columns: repeat(11, minmax(0, 1fr));
  }
  .md\\:grid-cols-12 {
    grid-template-columns: repeat(12, minmax(0, 1fr));
  }
}
@media (min-width: 1024px) {
  .lg\\:grid-cols-1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .lg\\:grid-cols-2 {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  .lg\\:grid-cols-3 {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  .lg\\:grid-cols-4 {
    grid-template-columns: repeat(4, minmax(0, 1fr));
  }
  .lg\\:grid-cols-5 {
    grid-template-columns: repeat(5, minmax(0, 1fr));
  }
  .lg\\:grid-cols-6 {
    grid-template-columns: repeat(6, minmax(0, 1fr));
  }
  .lg\\:grid-cols-7 {
    grid-template-columns: repeat(7, minmax(0, 1fr));
  }
  .lg\\:grid-cols-8 {
    grid-template-columns: repeat(8, minmax(0, 1fr));
  }
  .lg\\:grid-cols-9 {
    grid-template-columns: repeat(9, minmax(0, 1fr));
  }
  .lg\\:grid-cols-10 {
    grid-template-columns: repeat(10, minmax(0, 1fr));
  }
  .lg\\:grid-cols-11 {
    grid-template-columns: repeat(11, minmax(0, 1fr));
  }
  .lg\\:grid-cols-12 {
    grid-template-columns: repeat(12, minmax(0, 1fr));
  }
}
@media (min-width: 1280px) {
  .xl\\:grid-cols-1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .xl\\:grid-cols-2 {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  .xl\\:grid-cols-3 {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  .xl\\:grid-cols-4 {
    grid-template-columns: repeat(4, minmax(0, 1fr));
  }
  .xl\\:grid-cols-5 {
    grid-template-columns: repeat(5, minmax(0, 1fr));
  }
  .xl\\:grid-cols-6 {
    grid-template-columns: repeat(6, minmax(0, 1fr));
  }
  .xl\\:grid-cols-7 {
    grid-template-columns: repeat(7, minmax(0, 1fr));
  }
  .xl\\:grid-cols-8 {
    grid-template-columns: repeat(8, minmax(0, 1fr));
  }
  .xl\\:grid-cols-9 {
    grid-template-columns: repeat(9, minmax(0, 1fr));
  }
  .xl\\:grid-cols-10 {
    grid-template-columns: repeat(10, minmax(0, 1fr));
  }
  .xl\\:grid-cols-11 {
    grid-template-columns: repeat(11, minmax(0, 1fr));
  }
  .xl\\:grid-cols-12 {
    grid-template-columns: repeat(12, minmax(0, 1fr));
  }
}
@media (min-width: 1536px) {
  .\\32xl\\:grid-cols-1 {
    grid-template-columns: repeat(1, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-2 {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-3 {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-4 {
    grid-template-columns: repeat(4, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-5 {
    grid-template-columns: repeat(5, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-6 {
    grid-template-columns: repeat(6, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-7 {
    grid-template-columns: repeat(7, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-8 {
    grid-template-columns: repeat(8, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-9 {
    grid-template-columns: repeat(9, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-10 {
    grid-template-columns: repeat(10, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-11 {
    grid-template-columns: repeat(11, minmax(0, 1fr));
  }
  .\\32xl\\:grid-cols-12 {
    grid-template-columns: repeat(12, minmax(0, 1fr));
  }
}

/* === Box sizing === */
.box-border {
  box-sizing: border-box;
}
.box-content {
  box-sizing: content-box;
}

/* === Display === */
.block {
  display: block;
}
.flex {
  display: flex;
}
.hidden {
  display: none;
}
.inline {
  display: inline;
}
.inline-block {
  display: inline-block;
}
.inline-flex {
  display: inline-flex;
}
.inline-grid {
  display: inline-grid;
}
.grid {
  display: grid;
}

/* === Overflow === */
.overflow-auto {
  overflow: auto;
}
.overflow-hidden {
  overflow: hidden;
}
.overflow-scroll {
  overflow: scroll;
}

/* === Position === */
.absolute {
  position: absolute;
}
.fixed {
  position: fixed;
}
.static {
  position: static;
}
.sticky {
  position: sticky;
}
.relative {
  position: relative;
}

/* === Responsive design === */
@media (min-width: 640px) {
  .sm\\:flex {
    display: flex;
  }
  .sm\\:hidden {
    display: none;
  }
}
@media (min-width: 768px) {
  .md\\:flex {
    display: flex;
  }
  .md\\:hidden {
    display: none;
  }
}
@media (min-width: 1024px) {
  .lg\\:flex {
    display: flex;
  }
  .lg\\:hidden {
    display: none;
  }
}
@media (min-width: 1280px) {
  .xl\\:flex {
    display: flex;
  }
  .xl\\:hidden {
    display: none;
  }
}
@media (min-width: 1536px) {
  .\\32xl\\:flex {
    display: flex;
  }
  .\\32xl\\:hidden {
    display: none;
  }
}

/* === Height === */
.h-0 {
  height: 0;
}
.h-xs {
  height: var(--lumo-size-xs);
}
.h-s {
  height: var(--lumo-size-s);
}
.h-m {
  height: var(--lumo-size-m);
}
.h-l {
  height: var(--lumo-size-l);
}
.h-xl {
  height: var(--lumo-size-xl);
}
.h-auto {
  height: auto;
}
.h-full {
  height: 100%;
}
.h-screen {
  height: 100vh;
}

/* === Height (max) === */
.max-h-full {
  max-height: 100%;
}
.max-h-screen {
  max-height: 100vh;
}

/* === Height (min) === */
.min-h-0 {
  min-height: 0;
}
.min-h-full {
  min-height: 100%;
}
.min-h-screen {
  min-height: 100vh;
}

/* === Icon sizing === */
.icon-s {
  height: var(--lumo-icon-size-s);
  width: var(--lumo-icon-size-s);
}
.icon-m {
  height: var(--lumo-icon-size-m);
  width: var(--lumo-icon-size-m);
}
.icon-l {
  height: var(--lumo-icon-size-l);
  width: var(--lumo-icon-size-l);
}

/* === Width === */
.w-xs {
  width: var(--lumo-size-xs);
}
.w-s {
  width: var(--lumo-size-s);
}
.w-m {
  width: var(--lumo-size-m);
}
.w-l {
  width: var(--lumo-size-l);
}
.w-xl {
  width: var(--lumo-size-xl);
}
.w-auto {
  width: auto;
}
.w-full {
  width: 100%;
}

/* === Width (max) === */
.max-w-full {
  max-width: 100%;
}
.max-w-max {
  max-width: max-content;
}
.max-w-min {
  max-width: min-content;
}
.max-w-screen-sm {
  max-width: 640px;
}
.max-w-screen-md {
  max-width: 768px;
}
.max-w-screen-lg {
  max-width: 1024px;
}
.max-w-screen-xl {
  max-width: 1280px;
}
.max-w-screen-2xl {
  max-width: 1536px;
}

/* === Width (min) === */
.min-w-0 {
  min-width: 0;
}
.min-w-full {
  min-width: 100%;
}
.min-w-max {
  min-width: max-content;
}
.min-w-min {
  min-width: min-content;
}

/* === Margin === */
.m-auto {
  margin: auto;
}
.m-0 {
  margin: 0;
}
.m-xs {
  margin: var(--lumo-space-xs);
}
.m-s {
  margin: var(--lumo-space-s);
}
.m-m {
  margin: var(--lumo-space-m);
}
.m-l {
  margin: var(--lumo-space-l);
}
.m-xl {
  margin: var(--lumo-space-xl);
}

/* === Margin (bottom) === */
.mb-auto {
  margin-bottom: auto;
}
.mb-0 {
  margin-bottom: 0;
}
.mb-xs {
  margin-bottom: var(--lumo-space-xs);
}
.mb-s {
  margin-bottom: var(--lumo-space-s);
}
.mb-m {
  margin-bottom: var(--lumo-space-m);
}
.mb-l {
  margin-bottom: var(--lumo-space-l);
}
.mb-xl {
  margin-bottom: var(--lumo-space-xl);
}

/* === Margin (end) === */
.me-auto {
  margin-inline-end: auto;
}
.me-0 {
  margin-inline-end: 0;
}
.me-xs {
  margin-inline-end: var(--lumo-space-xs);
}
.me-s {
  margin-inline-end: var(--lumo-space-s);
}
.me-m {
  margin-inline-end: var(--lumo-space-m);
}
.me-l {
  margin-inline-end: var(--lumo-space-l);
}
.me-xl {
  margin-inline-end: var(--lumo-space-xl);
}

/* === Margin (horizontal) === */
.mx-auto {
  margin-left: auto;
  margin-right: auto;
}
.mx-0 {
  margin-left: 0;
  margin-right: 0;
}
.mx-xs {
  margin-left: var(--lumo-space-xs);
  margin-right: var(--lumo-space-xs);
}
.mx-s {
  margin-left: var(--lumo-space-s);
  margin-right: var(--lumo-space-s);
}
.mx-m {
  margin-left: var(--lumo-space-m);
  margin-right: var(--lumo-space-m);
}
.mx-l {
  margin-left: var(--lumo-space-l);
  margin-right: var(--lumo-space-l);
}
.mx-xl {
  margin-left: var(--lumo-space-xl);
  margin-right: var(--lumo-space-xl);
}

/* === Margin (left) === */
.ml-auto {
  margin-left: auto;
}
.ml-0 {
  margin-left: 0;
}
.ml-xs {
  margin-left: var(--lumo-space-xs);
}
.ml-s {
  margin-left: var(--lumo-space-s);
}
.ml-m {
  margin-left: var(--lumo-space-m);
}
.ml-l {
  margin-left: var(--lumo-space-l);
}
.ml-xl {
  margin-left: var(--lumo-space-xl);
}

/* === Margin (right) === */
.mr-auto {
  margin-right: auto;
}
.mr-0 {
  margin-right: 0;
}
.mr-xs {
  margin-right: var(--lumo-space-xs);
}
.mr-s {
  margin-right: var(--lumo-space-s);
}
.mr-m {
  margin-right: var(--lumo-space-m);
}
.mr-l {
  margin-right: var(--lumo-space-l);
}
.mr-xl {
  margin-right: var(--lumo-space-xl);
}

/* === Margin (start) === */
.ms-auto {
  margin-inline-start: auto;
}
.ms-0 {
  margin-inline-start: 0;
}
.ms-xs {
  margin-inline-start: var(--lumo-space-xs);
}
.ms-s {
  margin-inline-start: var(--lumo-space-s);
}
.ms-m {
  margin-inline-start: var(--lumo-space-m);
}
.ms-l {
  margin-inline-start: var(--lumo-space-l);
}
.ms-xl {
  margin-inline-start: var(--lumo-space-xl);
}

/* === Margin (top) === */
.mt-auto {
  margin-top: auto;
}
.mt-0 {
  margin-top: 0;
}
.mt-xs {
  margin-top: var(--lumo-space-xs);
}
.mt-s {
  margin-top: var(--lumo-space-s);
}
.mt-m {
  margin-top: var(--lumo-space-m);
}
.mt-l {
  margin-top: var(--lumo-space-l);
}
.mt-xl {
  margin-top: var(--lumo-space-xl);
}

/* === Margin (vertical) === */
.my-auto {
  margin-bottom: auto;
  margin-top: auto;
}
.my-0 {
  margin-bottom: 0;
  margin-top: 0;
}
.my-xs {
  margin-bottom: var(--lumo-space-xs);
  margin-top: var(--lumo-space-xs);
}
.my-s {
  margin-bottom: var(--lumo-space-s);
  margin-top: var(--lumo-space-s);
}
.my-m {
  margin-bottom: var(--lumo-space-m);
  margin-top: var(--lumo-space-m);
}
.my-l {
  margin-bottom: var(--lumo-space-l);
  margin-top: var(--lumo-space-l);
}
.my-xl {
  margin-bottom: var(--lumo-space-xl);
  margin-top: var(--lumo-space-xl);
}

/* === Padding === */
.p-0 {
  padding: 0;
}
.p-xs {
  padding: var(--lumo-space-xs);
}
.p-s {
  padding: var(--lumo-space-s);
}
.p-m {
  padding: var(--lumo-space-m);
}
.p-l {
  padding: var(--lumo-space-l);
}
.p-xl {
  padding: var(--lumo-space-xl);
}

/* === Padding (bottom) === */
.pb-0 {
  padding-bottom: 0;
}
.pb-xs {
  padding-bottom: var(--lumo-space-xs);
}
.pb-s {
  padding-bottom: var(--lumo-space-s);
}
.pb-m {
  padding-bottom: var(--lumo-space-m);
}
.pb-l {
  padding-bottom: var(--lumo-space-l);
}
.pb-xl {
  padding-bottom: var(--lumo-space-xl);
}

/* === Padding (end) === */
.pe-0 {
  padding-inline-end: 0;
}
.pe-xs {
  padding-inline-end: var(--lumo-space-xs);
}
.pe-s {
  padding-inline-end: var(--lumo-space-s);
}
.pe-m {
  padding-inline-end: var(--lumo-space-m);
}
.pe-l {
  padding-inline-end: var(--lumo-space-l);
}
.pe-xl {
  padding-inline-end: var(--lumo-space-xl);
}

/* === Padding (horizontal) === */
.px-0 {
  padding-left: 0;
  padding-right: 0;
}
.px-xs {
  padding-left: var(--lumo-space-xs);
  padding-right: var(--lumo-space-xs);
}
.px-s {
  padding-left: var(--lumo-space-s);
  padding-right: var(--lumo-space-s);
}
.px-m {
  padding-left: var(--lumo-space-m);
  padding-right: var(--lumo-space-m);
}
.px-l {
  padding-left: var(--lumo-space-l);
  padding-right: var(--lumo-space-l);
}
.px-xl {
  padding-left: var(--lumo-space-xl);
  padding-right: var(--lumo-space-xl);
}

/* === Padding (left) === */
.pl-0 {
  padding-left: 0;
}
.pl-xs {
  padding-left: var(--lumo-space-xs);
}
.pl-s {
  padding-left: var(--lumo-space-s);
}
.pl-m {
  padding-left: var(--lumo-space-m);
}
.pl-l {
  padding-left: var(--lumo-space-l);
}
.pl-xl {
  padding-left: var(--lumo-space-xl);
}

/* === Padding (right) === */
.pr-0 {
  padding-right: 0;
}
.pr-xs {
  padding-right: var(--lumo-space-xs);
}
.pr-s {
  padding-right: var(--lumo-space-s);
}
.pr-m {
  padding-right: var(--lumo-space-m);
}
.pr-l {
  padding-right: var(--lumo-space-l);
}
.pr-xl {
  padding-right: var(--lumo-space-xl);
}

/* === Padding (start) === */
.ps-0 {
  padding-inline-start: 0;
}
.ps-xs {
  padding-inline-start: var(--lumo-space-xs);
}
.ps-s {
  padding-inline-start: var(--lumo-space-s);
}
.ps-m {
  padding-inline-start: var(--lumo-space-m);
}
.ps-l {
  padding-inline-start: var(--lumo-space-l);
}
.ps-xl {
  padding-inline-start: var(--lumo-space-xl);
}

/* === Padding (top) === */
.pt-0 {
  padding-top: 0;
}
.pt-xs {
  padding-top: var(--lumo-space-xs);
}
.pt-s {
  padding-top: var(--lumo-space-s);
}
.pt-m {
  padding-top: var(--lumo-space-m);
}
.pt-l {
  padding-top: var(--lumo-space-l);
}
.pt-xl {
  padding-top: var(--lumo-space-xl);
}

/* === Padding (vertical) === */
.py-0 {
  padding-bottom: 0;
  padding-top: 0;
}
.py-xs {
  padding-bottom: var(--lumo-space-xs);
  padding-top: var(--lumo-space-xs);
}
.py-s {
  padding-bottom: var(--lumo-space-s);
  padding-top: var(--lumo-space-s);
}
.py-m {
  padding-bottom: var(--lumo-space-m);
  padding-top: var(--lumo-space-m);
}
.py-l {
  padding-bottom: var(--lumo-space-l);
  padding-top: var(--lumo-space-l);
}
.py-xl {
  padding-bottom: var(--lumo-space-xl);
  padding-top: var(--lumo-space-xl);
}

/* === Spacing === */
.space-xs > *:not(:last-child) {
  margin: var(--lumo-space-xs);
}
.space-s > *:not(:last-child) {
  margin: var(--lumo-space-s);
}
.space-m > *:not(:last-child) {
  margin: var(--lumo-space-m);
}
.space-l > *:not(:last-child) {
  margin: var(--lumo-space-l);
}
.space-xl > *:not(:last-child) {
  margin: var(--lumo-space-xl);
}

/* === Spacing (bottom) === */
.spacing-b-xs > *:not(:last-child) {
  margin-bottom: var(--lumo-space-xs);
}
.spacing-b-s > *:not(:last-child) {
  margin-bottom: var(--lumo-space-s);
}
.spacing-b-m > *:not(:last-child) {
  margin-bottom: var(--lumo-space-m);
}
.spacing-b-l > *:not(:last-child) {
  margin-bottom: var(--lumo-space-l);
}
.spacing-b-xl > *:not(:last-child) {
  margin-bottom: var(--lumo-space-xl);
}

/* === Spacing (end) === */
.spacing-e-xs > *:not(:last-child) {
  margin-inline-end: var(--lumo-space-xs);
}
.spacing-e-s > *:not(:last-child) {
  margin-inline-end: var(--lumo-space-s);
}
.spacing-e-m > *:not(:last-child) {
  margin-inline-end: var(--lumo-space-m);
}
.spacing-e-l > *:not(:last-child) {
  margin-inline-end: var(--lumo-space-l);
}
.spacing-e-xl > *:not(:last-child) {
  margin-inline-end: var(--lumo-space-xl);
}

/* === Spacing (horizontal) === */
.spacing-x-xs > *:not(:last-child) {
  margin-left: var(--lumo-space-xs);
  margin-right: var(--lumo-space-xs);
}
.spacing-x-s > *:not(:last-child) {
  margin-left: var(--lumo-space-s);
  margin-right: var(--lumo-space-s);
}
.spacing-x-m > *:not(:last-child) {
  margin-left: var(--lumo-space-m);
  margin-right: var(--lumo-space-m);
}
.spacing-x-l > *:not(:last-child) {
  margin-left: var(--lumo-space-l);
  margin-right: var(--lumo-space-l);
}
.spacing-x-xl > *:not(:last-child) {
  margin-left: var(--lumo-space-xl);
  margin-right: var(--lumo-space-xl);
}

/* === Spacing (left) === */
.spacing-l-xs > *:not(:last-child) {
  margin-left: var(--lumo-space-xs);
}
.spacing-l-s > *:not(:last-child) {
  margin-left: var(--lumo-space-s);
}
.spacing-l-m > *:not(:last-child) {
  margin-left: var(--lumo-space-m);
}
.spacing-l-l > *:not(:last-child) {
  margin-left: var(--lumo-space-l);
}
.spacing-l-xl > *:not(:last-child) {
  margin-left: var(--lumo-space-xl);
}

/* === Spacing (right) === */
.spacing-r-xs > *:not(:last-child) {
  margin-right: var(--lumo-space-xs);
}
.spacing-r-s > *:not(:last-child) {
  margin-right: var(--lumo-space-s);
}
.spacing-r-m > *:not(:last-child) {
  margin-right: var(--lumo-space-m);
}
.spacing-r-l > *:not(:last-child) {
  margin-right: var(--lumo-space-l);
}
.spacing-r-xl > *:not(:last-child) {
  margin-right: var(--lumo-space-xl);
}

/* === Spacing (start) === */
.spacing-s-xs > *:not(:last-child) {
  margin-inline-start: var(--lumo-space-xs);
}
.spacing-s-s > *:not(:last-child) {
  margin-inline-start: var(--lumo-space-s);
}
.spacing-s-m > *:not(:last-child) {
  margin-inline-start: var(--lumo-space-m);
}
.spacing-s-l > *:not(:last-child) {
  margin-inline-start: var(--lumo-space-l);
}
.spacing-s-xl > *:not(:last-child) {
  margin-inline-start: var(--lumo-space-xl);
}

/* === Spacing (top) === */
.spacing-t-xs > *:not(:last-child) {
  margin-top: var(--lumo-space-xs);
}
.spacing-t-s > *:not(:last-child) {
  margin-top: var(--lumo-space-s);
}
.spacing-t-m > *:not(:last-child) {
  margin-top: var(--lumo-space-m);
}
.spacing-t-l > *:not(:last-child) {
  margin-top: var(--lumo-space-l);
}
.spacing-t-xl > *:not(:last-child) {
  margin-top: var(--lumo-space-xl);
}

/* === Spacing (vertical) === */
.spacing-y-xs > *:not(:last-child) {
  margin-bottom: var(--lumo-space-xs);
  margin-top: var(--lumo-space-xs);
}
.spacing-y-s > *:not(:last-child) {
  margin-bottom: var(--lumo-space-s);
  margin-top: var(--lumo-space-s);
}
.spacing-y-m > *:not(:last-child) {
  margin-bottom: var(--lumo-space-m);
  margin-top: var(--lumo-space-m);
}
.spacing-y-l > *:not(:last-child) {
  margin-bottom: var(--lumo-space-l);
  margin-top: var(--lumo-space-l);
}
.spacing-y-xl > *:not(:last-child) {
  margin-bottom: var(--lumo-space-xl);
  margin-top: var(--lumo-space-xl);
}

/* === Font size === */
.text-2xs {
  font-size: var(--lumo-font-size-xxs);
}
.text-xs {
  font-size: var(--lumo-font-size-xs);
}
.text-s {
  font-size: var(--lumo-font-size-s);
}
.text-m {
  font-size: var(--lumo-font-size-m);
}
.text-l {
  font-size: var(--lumo-font-size-l);
}
.text-xl {
  font-size: var(--lumo-font-size-xl);
}
.text-2xl {
  font-size: var(--lumo-font-size-xxl);
}
.text-3xl {
  font-size: var(--lumo-font-size-xxxl);
}

/* === Font weight === */
.font-thin {
  font-weight: 100; 
}
.font-extralight {
  font-weight: 200; 
}
.font-light {
  font-weight: 300; 
}
.font-normal {
  font-weight: 400; 
}
.font-medium {
  font-weight: 500; 
}
.font-semibold {
  font-weight: 600; 
}
.font-bold {
  font-weight: 700; 
}
.font-extrabold {
  font-weight: 800; 
}
.font-black {
  font-weight: 900; 
}

/* === Line height === */
.leading-none {
  line-height: 1;
}
.leading-xs {
  line-height: var(--lumo-line-height-xs);
}
.leading-s {
  line-height: var(--lumo-line-height-s);
}
.leading-m {
  line-height: var(--lumo-line-height-m);
}

/* === List style type === */
.list-none {
  list-style-type: none;
}

/* === Text alignment === */
.text-left {
  text-align: left;
}
.text-center {
  text-align: center;
}
.text-right {
  text-align: right;
}
.text-justify {
  text-align: justify;
}

/* === Text color === */
.text-header {
  color: var(--lumo-header-text-color);
}
.text-body {
  color: var(--lumo-body-text-color);
}
.text-secondary {
  color: var(--lumo-secondary-text-color);
}
.text-tertiary {
  color: var(--lumo-tertiary-text-color);
}
.text-disabled {
  color: var(--lumo-disabled-text-color);
}
.text-primary {
  color: var(--lumo-primary-text-color);
}
.text-primary-contrast {
  color: var(--lumo-primary-contrast-color);
}
.text-error {
  color: var(--lumo-error-text-color);
}
.text-error-contrast {
  color: var(--lumo-error-contrast-color);
}
.text-success {
  color: var(--lumo-success-text-color);
}
.text-success-contrast {
  color: var(--lumo-success-contrast-color);
}

/* === Text overflow === */
.overflow-clip {
  text-overflow: clip;
}
.overflow-ellipsis {
  text-overflow: ellipsis;
}

/* === Text transform === */
.capitalize {
  text-transform: capitalize;
}
.lowercase {
  text-transform: lowercase;
}
.uppercase {
  text-transform: uppercase;
}

/* === Whitespace === */
.whitespace-normal {
  white-space: normal;
}
.whitespace-nowrap {
  white-space: nowrap;
}
.whitespace-pre {
  white-space: pre;
}
.whitespace-pre-line {
  white-space: pre-line;
}
.whitespace-pre-wrap {
  white-space: pre-wrap;
}

/* === Responsive design === */
@media (min-width: 640px) {
  .sm\\:text-2xs {
    font-size: var(--lumo-font-size-xxs);
  }
  .sm\\:text-xs {
    font-size: var(--lumo-font-size-xs);
  }
  .sm\\:text-s {
    font-size: var(--lumo-font-size-s);
  }
  .sm\\:text-m {
    font-size: var(--lumo-font-size-m);
  }
  .sm\\:text-l {
    font-size: var(--lumo-font-size-l);
  }
  .sm\\:text-xl {
    font-size: var(--lumo-font-size-xl);
  }
  .sm\\:text-2xl {
    font-size: var(--lumo-font-size-xxl);
  }
  .sm\\:text-3xl {
    font-size: var(--lumo-font-size-xxxl);
  }
}
@media (min-width: 768px) {
  .md\\:text-2xs {
    font-size: var(--lumo-font-size-xxs);
  }
  .md\\:text-xs {
    font-size: var(--lumo-font-size-xs);
  }
  .md\\:text-s {
    font-size: var(--lumo-font-size-s);
  }
  .md\\:text-m {
    font-size: var(--lumo-font-size-m);
  }
  .md\\:text-l {
    font-size: var(--lumo-font-size-l);
  }
  .md\\:text-xl {
    font-size: var(--lumo-font-size-xl);
  }
  .md\\:text-2xl {
    font-size: var(--lumo-font-size-xxl);
  }
  .md\\:text-3xl {
    font-size: var(--lumo-font-size-xxxl);
  }
}
@media (min-width: 1024px) {
  .lg\\:text-2xs {
    font-size: var(--lumo-font-size-xxs);
  }
  .lg\\:text-xs {
    font-size: var(--lumo-font-size-xs);
  }
  .lg\\:text-s {
    font-size: var(--lumo-font-size-s);
  }
  .lg\\:text-m {
    font-size: var(--lumo-font-size-m);
  }
  .lg\\:text-l {
    font-size: var(--lumo-font-size-l);
  }
  .lg\\:text-xl {
    font-size: var(--lumo-font-size-xl);
  }
  .lg\\:text-2xl {
    font-size: var(--lumo-font-size-xxl);
  }
  .lg\\:text-3xl {
    font-size: var(--lumo-font-size-xxxl);
  }
}
@media (min-width: 1280px) {
  .xl\\:text-2xs {
    font-size: var(--lumo-font-size-xxs);
  }
  .xl\\:text-xs {
    font-size: var(--lumo-font-size-xs);
  }
  .xl\\:text-s {
    font-size: var(--lumo-font-size-s);
  }
  .xl\\:text-m {
    font-size: var(--lumo-font-size-m);
  }
  .xl\\:text-l {
    font-size: var(--lumo-font-size-l);
  }
  .xl\\:text-xl {
    font-size: var(--lumo-font-size-xl);
  }
  .xl\\:text-2xl {
    font-size: var(--lumo-font-size-xxl);
  }
  .xl\\:text-3xl {
    font-size: var(--lumo-font-size-xxxl);
  }
}
@media (min-width: 1536px) {
  .\\32xl\\:text-2xs {
    font-size: var(--lumo-font-size-xxs);
  }
  .\\32xl\\:text-xs {
    font-size: var(--lumo-font-size-xs);
  }
  .\\32xl\\:text-s {
    font-size: var(--lumo-font-size-s);
  }
  .\\32xl\\:text-m {
    font-size: var(--lumo-font-size-m);
  }
  .\\32xl\\:text-l {
    font-size: var(--lumo-font-size-l);
  }
  .\\32xl\\:text-xl {
    font-size: var(--lumo-font-size-xl);
  }
  .\\32xl\\:text-2xl {
    font-size: var(--lumo-font-size-xxl);
  }
  .\\32xl\\:text-3xl {
    font-size: var(--lumo-font-size-xxxl);
  }
}
`),xe=(o(22),o(21),o(26),o(3));o(27);
/**
 * @license
 * Copyright (c) 2021 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
const ye=xe.a`
  [theme~='badge'] {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
    padding: 0.4em calc(0.5em + var(--lumo-border-radius-s) / 4);
    color: var(--lumo-primary-text-color);
    background-color: var(--lumo-primary-color-10pct);
    border-radius: var(--lumo-border-radius-s);
    font-family: var(--lumo-font-family);
    font-size: var(--lumo-font-size-s);
    line-height: 1;
    font-weight: 500;
    text-transform: initial;
    letter-spacing: initial;
    min-width: calc(var(--lumo-line-height-xs) * 1em + 0.45em);
  }

  /* Ensure proper vertical alignment */
  [theme~='badge']::before {
    display: inline-block;
    content: '\\2003';
    width: 0;
  }

  [theme~='badge'][theme~='small'] {
    font-size: var(--lumo-font-size-xxs);
    line-height: 1;
  }

  /* Colors */

  [theme~='badge'][theme~='success'] {
    color: var(--lumo-success-text-color);
    background-color: var(--lumo-success-color-10pct);
  }

  [theme~='badge'][theme~='error'] {
    color: var(--lumo-error-text-color);
    background-color: var(--lumo-error-color-10pct);
  }

  [theme~='badge'][theme~='contrast'] {
    color: var(--lumo-contrast-80pct);
    background-color: var(--lumo-contrast-5pct);
  }

  /* Primary */

  [theme~='badge'][theme~='primary'] {
    color: var(--lumo-primary-contrast-color);
    background-color: var(--lumo-primary-color);
  }

  [theme~='badge'][theme~='success'][theme~='primary'] {
    color: var(--lumo-success-contrast-color);
    background-color: var(--lumo-success-color);
  }

  [theme~='badge'][theme~='error'][theme~='primary'] {
    color: var(--lumo-error-contrast-color);
    background-color: var(--lumo-error-color);
  }

  [theme~='badge'][theme~='contrast'][theme~='primary'] {
    color: var(--lumo-base-color);
    background-color: var(--lumo-contrast);
  }

  /* Links */

  [theme~='badge'][href]:hover {
    text-decoration: none;
  }

  /* Icon */

  [theme~='badge'] iron-icon {
    margin: -0.25em 0;
    --iron-icon-width: 1.5em;
    --iron-icon-height: 1.5em;
  }

  [theme~='badge'] iron-icon:first-child {
    margin-left: -0.375em;
  }

  [theme~='badge'] iron-icon:last-child {
    margin-right: -0.375em;
  }

  [theme~='badge'][icon] {
    min-width: 0;
    padding: 0;
    font-size: 1rem;
    --iron-icon-width: var(--lumo-icon-size-m);
    --iron-icon-height: var(--lumo-icon-size-m);
  }

  [theme~='badge'][icon][theme~='small'] {
    --iron-icon-width: var(--lumo-icon-size-s);
    --iron-icon-height: var(--lumo-icon-size-s);
  }

  /* Empty */

  [theme~='badge']:not([icon]):empty {
    min-width: 0;
    width: 1em;
    height: 1em;
    padding: 0;
    border-radius: 50%;
    background-color: var(--lumo-primary-color);
  }

  [theme~='badge'][theme~='small']:not([icon]):empty {
    width: 0.75em;
    height: 0.75em;
  }

  [theme~='badge'][theme~='contrast']:not([icon]):empty {
    background-color: var(--lumo-contrast);
  }

  [theme~='badge'][theme~='success']:not([icon]):empty {
    background-color: var(--lumo-success-color);
  }

  [theme~='badge'][theme~='error']:not([icon]):empty {
    background-color: var(--lumo-error-color);
  }

  /* Pill */

  [theme~='badge'][theme~='pill'] {
    --lumo-border-radius-s: 1em;
  }

  /* RTL specific styles */

  [dir='rtl'][theme~='badge'] iron-icon:first-child {
    margin-right: -0.375em;
    margin-left: 0;
  }

  [dir='rtl'][theme~='badge'] iron-icon:last-child {
    margin-left: -0.375em;
    margin-right: 0;
  }
`;Object(xe.b)("",ye,{moduleId:"lumo-badge"});const be=(e,t,o)=>{const n=new CSSStyleSheet;n.replaceSync(((e,t)=>{const o=/(?:@media\s(.+?))?(?:\s{)?\@import\surl\((.+?)\);(?:})?/g;for(var n,r=e;null!==(n=o.exec(e));){r=r.replace(n[0],"");const e=document.createElement("link");e.rel="stylesheet",e.href=n[2],n[1]&&(e.media=n[1]),t===document?document.head.appendChild(e):t.appendChild(e)}return r})(e,t)),t.adoptedStyleSheets=o?[n,...t.adoptedStyleSheets]:[...t.adoptedStyleSheets,n]},we=(e,t)=>{!function(e,t=!1){const o=document.createElement("template");o.innerHTML=e,document.head[t?"insertBefore":"appendChild"](o.content,document.head.firstChild)}(`<custom-style><style include="${e}"></style></custom-style>`,!0)},_e=e=>{const t=ge.a.import(e,"template");return t&&Object(fe.c)(t,"").map(e=>e.textContent).join(" ")};window.Vaadin=window.Vaadin||{},window.Vaadin._vaadintheme_x4galaxygenerator_globalCss=window.Vaadin._vaadintheme_x4galaxygenerator_globalCss||[];(e=>{(0===window.Vaadin._vaadintheme_x4galaxygenerator_globalCss.length||!window.Vaadin._vaadintheme_x4galaxygenerator_globalCss.includes(e)&&e!==document)&&(be(ve.toString(),e),window.Vaadin._vaadintheme_x4galaxygenerator_globalCss.push(e)),document._vaadintheme_x4galaxygenerator_componentCss||(document._vaadintheme_x4galaxygenerator_componentCss=!0);e instanceof ShadowRoot?(be(_e("lumo-typography"),e,!0),be(_e("lumo-color"),e,!0),be(_e("lumo-spacing"),e,!0),be(_e("lumo-badge"),e,!0)):document._vaadinthemelumoimports_||(we("lumo-typography"),we("lumo-color"),we("lumo-spacing"),we("lumo-badge"),document._vaadinthemelumoimports_=!0)})(document)}]);