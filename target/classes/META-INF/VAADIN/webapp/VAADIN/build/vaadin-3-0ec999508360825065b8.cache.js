(window.webpackJsonp=window.webpackJsonp||[]).push([[3],{195:function(e,t){!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Date Picker","vaadin-date-picker")};class t{constructor(e){this.initial=e,this.index=0,this.value=0}static compare(e,t){return e.index<t.index?-1:e.index>t.index?1:0}}window.Vaadin.Flow.datepickerConnector={initLazy:n=>e((function(n){if(n.$connector)return;n.$connector={},n.$connector.dayPart=new t("22"),n.$connector.monthPart=new t("11"),n.$connector.yearPart=new t("1987"),n.$connector.parts=[n.$connector.dayPart,n.$connector.monthPart,n.$connector.yearPart];let i="en-us";n.addEventListener("blur",e(e=>{!e.target.value&&e.target.invalid&&console.warn("Invalid value in the DatePicker.")}));const r=e((function(e){return e.replace(/[^\x00-\x7F]/g,"")})),o=e((function(){let e="";try{e=n._inputValue}catch(t){e=n.value||""}return e}));n.$connector.setLocale=e((function(a){try{(new Date).toLocaleDateString(a)}catch(e){a="en-US",console.warn("The locale is not supported, using default locale setting(en-US).")}let c=!1,s=o();"undefined"!==n.i18n.parseDate&&s&&(c=n.i18n.parseDate(s));let d=new Date(Date.UTC(n.$connector.yearPart.initial,n.$connector.monthPart.initial-1,n.$connector.dayPart.initial)),l=r(d.toLocaleDateString(a,{timeZone:"UTC"}));n.$connector.parts.forEach((function(e){e.index=l.indexOf(e.initial)})),n.$connector.parts.sort(t.compare),n.$connector.regex=l.replace(/[-[\]{}()*+?.,\\^$|#\s]/g,"\\$&").replace(n.$connector.dayPart.initial,"(\\d{1,2})").replace(n.$connector.monthPart.initial,"(\\d{1,2})").replace(n.$connector.yearPart.initial,"(\\d{1,4})"),n.i18n.formatDate=e((function(e){let t=n._parseDate(`${e.year}-${e.month+1}-${e.day}`);return t.setHours(12),r(t.toLocaleDateString(a))})),n.i18n.parseDate=e((function(e){if(0==(e=r(e)).length)return;let t=e.match(n.$connector.regex);if(t&&4==t.length){for(let e=1;e<4;e++)n.$connector.parts[e-1].value=parseInt(t[e]);return{day:n.$connector.dayPart.value,month:n.$connector.monthPart.value-1,year:n.$connector.yearPart.value}}return!1})),""===s?i=a:c&&(n._selectedDate=n._parseDate(`${c.year}-${c.month+1}-${c.day}`))}))}))(n)}}()},196:function(e,t){window.Vaadin=window.Vaadin||{},window.Vaadin.Flow=window.Vaadin.Flow||{},window.Vaadin.Flow.dndConnector={__ondragenterListener:function(e){const t=e.currentTarget.__dropEffect;e.currentTarget.hasAttribute("disabled")||(t&&(e.dataTransfer.dropEffect=t),t&&"none"!==t&&(e.currentTarget.classList.contains("v-drag-over-target")?e.currentTarget["__skip-leave"]=!0:e.currentTarget.classList.add("v-drag-over-target"),e.preventDefault(),e.stopPropagation()))},__ondragoverListener:function(e){if(!e.currentTarget.hasAttribute("disabled")){const t=e.currentTarget.__dropEffect;t&&(e.dataTransfer.dropEffect=t),e.preventDefault(),e.stopPropagation()}},__ondragleaveListener:function(e){e.currentTarget["__skip-leave"]?e.currentTarget["__skip-leave"]=!1:e.currentTarget.classList.remove("v-drag-over-target"),e.stopPropagation()},__ondropListener:function(e){const t=e.currentTarget.__dropEffect;t&&(e.dataTransfer.dropEffect=t),e.currentTarget.classList.remove("v-drag-over-target"),e.preventDefault(),e.stopPropagation()},updateDropTarget:function(e){e.__active?(e.addEventListener("dragenter",this.__ondragenterListener,!1),e.addEventListener("dragover",this.__ondragoverListener,!1),e.addEventListener("dragleave",this.__ondragleaveListener,!1),e.addEventListener("drop",this.__ondropListener,!1)):(e.removeEventListener("dragenter",this.__ondragenterListener,!1),e.removeEventListener("dragover",this.__ondragoverListener,!1),e.removeEventListener("dragleave",this.__ondragleaveListener,!1),e.removeEventListener("drop",this.__ondropListener,!1),e.classList.remove("v-drag-over-target"))},__dragstartListener:function(e){e.stopPropagation(),e.dataTransfer.setData("text/plain",""),e.currentTarget.hasAttribute("disabled")?e.preventDefault():(e.currentTarget.__effectAllowed&&(e.dataTransfer.effectAllowed=e.currentTarget.__effectAllowed),e.currentTarget.classList.add("v-dragged"))},__dragendListener:function(e){e.currentTarget.classList.remove("v-dragged")},updateDragSource:function(e){e.draggable?(e.addEventListener("dragstart",this.__dragstartListener,!1),e.addEventListener("dragend",this.__dragendListener,!1)):(e.removeEventListener("dragstart",this.__dragstartListener,!1),e.removeEventListener("dragend",this.__dragendListener,!1))}}},197:function(e,t){!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Grid Pro","vaadin-grid-pro")};window.Vaadin.Flow.gridProConnector={setEditModeRenderer:(t,n)=>e((function(t,n){t.editModeRenderer=e((function(e){e.appendChild(n),this._grid._cancelStopEdit(),n.focus()})),t._setEditorValue=function(e,t){},t._getEditorValue=function(e){}}))(t,n),patchEditModeRenderer:t=>e((function(t){t.__editModeRenderer=e((function(e,t,n){const i=e.assignedSlot.parentNode,r=t._grid;if(r.__edited&&r.__edited.model.item.key!==n.item.key)return void r._stopEdit();const o=t._getEditorTagName(i);e.firstElementChild&&e.firstElementChild.localName.toLowerCase()===o||(e.innerHTML=`<${o}></${o}>`)}))}))(t)}}()},198:function(e,t){window.Vaadin.Flow.menubarConnector={initLazy:function(e){var t;e.$connector||(e.$connector={updateButtons:(t=function(){e.shadowRoot?(e.items.forEach(e=>e.disabled=e.component.disabled),e.items=e.items.filter(e=>!e.component.hidden),e._buttons.forEach(e=>{e.item&&e.item.component&&e.addEventListener("click",t=>{-1===t.composedPath().indexOf(e.item.component)&&e.item.component.click()})})):setTimeout(()=>e.$connector.updateButtons())},window.Vaadin.Flow.tryCatchWrapper(t,"Vaadin Menu Bar","vaadin-menu-bar"))})}}},199:function(e,t){window.Vaadin.Flow.messageListConnector={setItems:(e,t,n)=>{return(i=function(e,t,n){const i=new Intl.DateTimeFormat(n,{year:"numeric",month:"short",day:"numeric",hour:"numeric",minute:"numeric"});e.items=t.map(e=>e.time?Object.assign(e,{time:i.format(new Date(e.time))}):e)},window.Vaadin.Flow.tryCatchWrapper(i,"Vaadin Message List","vaadin-messages"))(e,t,n);var i}}},200:function(e,t){!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Select","vaadin-select")};window.Vaadin.Flow.selectConnector={initLazy:t=>e((function(t){const n=e((function(){for(let e=0;e<t.childElementCount;e++){const n=t.children[e];if("VAADIN-LIST-BOX"===n.tagName.toUpperCase())return n}}));t.$connector||(t.$connector={},t.renderer=e((function(e){const t=n();t&&(e.firstChild&&e.removeChild(e.firstChild),e.appendChild(t))})))}))(t)}}()},201:function(e,t){!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Time Picker","vaadin-time-picker")};window.Vaadin.Flow.timepickerConnector={initLazy:t=>e((function(t){if(t.$connector)return;t.$connector={};const n=function(e,t){const n=t.toLocaleTimeString(e);let i=n.match(/[^\d\u0660-\u0669]+$/g);return i||(i=n.match(/^[^\d\u0660-\u0669]+/g)),i&&(i=i[0].trim()),i},i=new Date("August 19, 1975 23:15:30"),r=new Date("August 19, 1975 05:15:30"),o={"\\u0660":"0","\\u0661":"1","\\u0662":"2","\\u0663":"3","\\u0664":"4","\\u0665":"5","\\u0666":"6","\\u0667":"7","\\u0668":"8","\\u0669":"9"},a=function(e){return e.replace(/[\u0660-\u0669]/g,(function(e){const t="\\u0"+e.charCodeAt(0).toString(16);return o[t]}))},c=function(e){return parseInt(a(e))},s=/[[\.][\d\u0660-\u0669]{1,3}$/;t.$connector.setLocale=e((function(o){let d;t.value&&""!==t.value&&(d=t.i18n.parseTime(t.value));try{i.toLocaleTimeString(o)}catch(e){throw o="en-US",new Error("vaadin-time-picker: The locale "+o+" is not supported, falling back to default locale setting(en-US).")}const l=function(e){return n(e,i)}(o),u=function(e){return n(e,r)}(o);let h=i.toLocaleTimeString(o);l&&h.startsWith(l)&&(h=h.replace(l,""));const f=h.match(/[^\u0660-\u0669\s\d]/),p=new RegExp("([\\d\\u0660-\\u0669]){1,2}(?:"+f+")?","g"),m=function(){return t.step&&t.step<1};let g,v;let _,y;t.i18n={formatTime:e((function(e){if(e){let n=new Date;n.setHours(e.hours),n.setMinutes(e.minutes),n.setSeconds(void 0!==e.seconds?e.seconds:0);let i=n.toLocaleTimeString(o,(v&&g===t.step||(v={hour:"numeric",minute:"numeric",second:t.step&&t.step<60?"numeric":void 0},g=t.step),v));return i=function(e,t){if(m()){let n=e;if(e.endsWith(u)?n=e.replace(" "+u,""):e.endsWith(l)&&(n=e.replace(" "+l,"")),t){let e=t<10?"0":"";e+=t<100?"0":"",e+=t,n+="."+e}else n+=".000";return e.endsWith(u)?n=n+" "+u:e.endsWith(l)&&(n=n+" "+l),n}return e}(i,e.milliseconds),i}})),parseTime:e((function(e){if(e&&e===_&&y)return y;if(e){const n=e.search(l),i=e.search(u);let r=e.replace(u,"").replace(l,"").trim();p.lastIndex=0;let o=p.exec(r);if(o){o=c(o[0].replace(f,"")),n!==i&&(12===o&&-1!==i?o=0:o+=-1!==n&&12!==o?12:0);const d=p.exec(r),l=d&&p.exec(r);let u=l&&m()&&s.exec(r);return u&&u.index<=l.index&&(u=void 0),y=void 0!==o&&{hours:o,minutes:d?c(d[0].replace(f,"")):0,seconds:l?c(l[0].replace(f,"")):0,milliseconds:d&&l&&u?(t=u[0].replace(".",""),1===(t=a(t)).length?t+="00":2===t.length&&(t+="0"),parseInt(t)):0},_=e,y}}var t}))},d&&function e(t,n,i=0){t()?n():setTimeout(()=>e(t,n,200),i)}(()=>t.shadowRoot,()=>{const e=t.i18n.formatTime(d);t.__inputElement.value!==e&&(t.__inputElement.value=e,t.__dropdownElement.value=e,t.__onInputChange())})}))}))(t)}}()},202:function(e,t){!function(){let e;customElements.whenDefined("vaadin-text-field").then(()=>{class t extends(customElements.get("vaadin-text-field")){static get template(){return e||(e=super.template.cloneNode(!0),e.innerHTML+='<style>\n                  :host {\n                    width: 8em;\n                  }\n\n                  :host([dir="rtl"]) [part="input-field"] {\n                    direction: ltr;\n                  }\n\n                  :host([dir="rtl"]) [part="value"]::placeholder {\n                    direction: rtl;\n                  }\n\n                  :host([dir="rtl"]) [part="input-field"] ::slotted(input)::placeholder {\n                    direction: rtl;\n                  }\n\n                  :host([dir="rtl"]) [part="value"]:-ms-input-placeholder,\n                  :host([dir="rtl"]) [part="input-field"] ::slotted(input):-ms-input-placeholder {\n                    direction: rtl;\n                  }\n\n                  :host([dir="rtl"]:not([has-controls])) [part="value"]::placeholder {\n                    text-align: left;\n                  }\n\n                  :host([dir="rtl"]:not([has-controls])) [part="input-field"] ::slotted(input)::placeholder {\n                    text-align: left;\n                  }\n\n                  :host([dir="rtl"]:not([has-controls])) [part="value"]:-ms-input-placeholder,\n                  :host([dir="rtl"]:not([has-controls])) [part="input-field"] ::slotted(input):-ms-input-placeholder {\n                    text-align: left;\n                  }\n\n                  :host([dir="rtl"]) [part="value"],\n                  :host([dir="rtl"]) [part="input-field"] ::slotted(input) {\n                    --_lumo-text-field-overflow-mask-image: linear-gradient(to left, transparent, #000 1.25em) !important;\n                  }\n            </style>'),e}static get is(){return"vaadin-big-decimal-field"}static get properties(){return{_decimalSeparator:{type:String,value:".",observer:"__decimalSeparatorChanged"}}}ready(){super.ready(),this.inputElement.setAttribute("inputmode","decimal")}__decimalSeparatorChanged(e,t){this._enabledCharPattern="[\\d-+"+e+"]",this.value&&t&&(this.value=this.value.split(t).join(e))}}customElements.define(t.is,t)})}()},268:function(e,t,n){"use strict";n.r(t),n.d(t,"addCssBlock",(function(){return p}));n(110),n(132);var i=n(37),r=n(36),o=n(90);!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Combo Box","vaadin-combo-box")};window.Vaadin.Flow.comboBoxConnector={initLazy:t=>e((function(t){if(t.$connector)return;t.$connector={};const n={};let o={},a="";const c=new window.Vaadin.ComboBoxPlaceholder,s=Math.max(2*t.pageSize,500),d=(()=>{let e="",n=!1;return{needsDataCommunicatorReset:()=>n=!0,getLastFilterSentToServer:()=>e,requestData:(i,r,o)=>{const a=r-i,c=o.filter;t.$server.setRequestedRange(i,a,c),e=c,n&&(t.$server.resetDataCommunicator(),n=!1)}}})(),l=(e=Object.keys(n))=>{e.forEach(e=>{n[e]([],t.size),delete n[e];const i=parseInt(e)*t.pageSize,r=i+t.pageSize,o=Math.min(r,t.filteredItems.length);for(let e=i;e<o;e++)t.filteredItems[e]=c})};t.dataProvider=function(e,c){if(e.pageSize!=t.pageSize)throw"Invalid pageSize";if(t._clientSideFilter){if(o[0])return void g(o[0],c);e.filter=""}if(e.filter!==a)return o={},a=e.filter,void(this._debouncer=i.a.debounce(this._debouncer,r.d.after(500),()=>{if(d.getLastFilterSentToServer()===e.filter&&d.needsDataCommunicatorReset(),e.filter!==a)throw new Error("Expected params.filter to be '"+a+"' but was '"+e.filter+"'");l(),t.dataProvider(e,c)}));if(o[e.page])m(e.page,c);else{n[e.page]=c;const o=Object.keys(n).map(e=>parseInt(e)),a=Math.min(...o),u=Math.max(...o);if(o.length*e.pageSize>s)e.page===a?l([String(u)]):l([String(a)]),t.dataProvider(e,c);else if(u-a+1!==o.length)l();else{const t=e.pageSize*a,n=e.pageSize*(u+1);this._debouncer&&this._debouncer.isActive()?this._debouncer=i.a.debounce(this._debouncer,r.d.after(200),()=>d.requestData(t,n,e)):d.requestData(t,n,e)}}},t.$connector.filter=e((function(e,n){return n=n?n.toString().toLowerCase():"",t._getItemLabel(e).toString().toLowerCase().indexOf(n)>-1})),t.$connector.set=e((function(e,i,r){if(r!=d.getLastFilterSentToServer())return;if(e%t.pageSize!=0)throw"Got new data to index "+e+" which is not aligned with the page size of "+t.pageSize;if(0===e&&0===i.length&&n[0])return void(o[0]=[]);const a=e/t.pageSize,c=Math.ceil(i.length/t.pageSize);for(let e=0;e<c;e++){let n=a+e,r=i.slice(e*t.pageSize,(e+1)*t.pageSize);o[n]=r}})),t.$connector.updateData=e((function(e){for(let n=0;n<e.length;n++){let i=e[n];for(let e=0;e<t.filteredItems.length;e++)if(t.filteredItems[e].key===i.key){t.set("filteredItems."+e,i);break}}})),t.$connector.updateSize=e((function(e){t._clientSideFilter||(t.size=e)})),t.$connector.reset=e((function(){l(),o={},t.clearCache()})),t.$connector.confirm=e((function(e,i){if(i!=d.getLastFilterSentToServer())return;let r=Object.getOwnPropertyNames(n);for(let e=0;e<r.length;e++){let t=r[e];o[t]&&m(t,n[t])}t.$server.confirmUpdate(e)})),customElements.whenDefined("vaadin-combo-box").then(e(()=>{const e=t.$.overlay._isItemSelected;t.$.overlay._isItemSelected=(n,i,r)=>{let o=e.call(t,n,i,r);return t._selectedKey&&(t.filteredItems.indexOf(i)>-1?delete t._selectedKey:o=o||n.key===t._selectedKey),o}})),t.$connector.enableClientValidation=e((function(e){let n=null;t.$&&(n=t.$.input),n?(e?(p(t),f(n)):(u(t),h(n,t)),t.validate()):setTimeout((function(){t.$connector.enableClientValidation(e)}),10)}));const u=e((function(e){void 0===e.$checkValidity&&(e.$checkValidity=e.checkValidity,e.checkValidity=function(){return!t.invalid}),void 0===e.$validate&&(e.$validate=e.validate,e.validate=function(){return!(t.focusElement.invalid=t.invalid)})})),h=e((function(e,t){void 0===e.$checkValidity&&(e.$checkValidity=e.checkValidity,e.checkValidity=function(){return!t.invalid})})),f=e((function(e){e.$checkValidity&&(e.checkValidity=e.$checkValidity,delete e.$checkValidity)})),p=e((function(e){e.$checkValidity&&(e.checkValidity=e.$checkValidity,delete e.$checkValidity),e.$validate&&(e.validate=e.$validate,delete e.$validate)})),m=e((function(e,n){let i=o[e];t._clientSideFilter?g(i,n):(delete o[e],n(i,t.size))})),g=e((function(e,n){let i=e;t.filter&&(i=e.filter(e=>t.$connector.filter(e,t.filter))),n(i,i.length)}));t.addEventListener("opened-changed",e(e=>{e.detail.value&&(t.$.overlay._selector._manageFocus=()=>{})})),t.addEventListener("custom-value-set",e(e=>e.preventDefault()))}))(t)}}(),window.Vaadin.ComboBoxPlaceholder=o.a;n(55);var a=n(57);!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Context Menu","vaadin-context-menu-flow")};window.Vaadin.Flow.contextMenuConnector={init:t=>e((function(t){t.$contextMenuConnector||(t.$contextMenuConnector={openOnHandler:e((function(e){e.preventDefault(),e.stopPropagation(),this.$contextMenuConnector.openEvent=e;let n={};t.getContextMenuBeforeOpenDetail&&(n=t.getContextMenuBeforeOpenDetail(e)),t.dispatchEvent(new CustomEvent("vaadin-context-menu-before-open",{detail:n}))})),updateOpenOn:e((function(n){this.removeListener(),this.openOnEventType=n,customElements.whenDefined("vaadin-context-menu").then(e(()=>{a.b[n]?a.a(t,n,this.openOnHandler):t.addEventListener(n,this.openOnHandler)}))})),removeListener:e((function(){this.openOnEventType&&(a.b[this.openOnEventType]?a.e(t,this.openOnEventType,this.openOnHandler):t.removeEventListener(this.openOnEventType,this.openOnHandler))})),openMenu:e((function(e){e.open(this.openEvent)})),removeConnector:e((function(){this.removeListener(),t.$contextMenuConnector=void 0}))})}))(t),generateItems:(t,n,i)=>e((function(e,t,n){e._containerNodeId=n;const i=function(e){const n=function(e){try{return window.Vaadin.Flow.clients[t].getByNodeId(e)}catch(n){console.error("Could not get node %s from app %s",e,t),console.error(n)}}(e._containerNodeId);return n&&Array.from(n.children).map(e=>{const t={component:e,checked:e._checked};return"VAADIN-CONTEXT-MENU-ITEM"==e.tagName&&e._containerNodeId&&(t.children=i(e)),e._item=t,t})},r=i(e);e.items=r}))(t,n,i),setChecked:(t,n)=>e((function(e,t){e._item&&(e._item.checked=t)}))(t,n)}}();n(195),n(196),n(56);var c=n(47),s=n(33);class d extends s.a{static get template(){return c.a`
    <style>
      @keyframes flow-component-renderer-appear {
        to {
          opacity: 1;
        }
      }
      :host {
        animation: 1ms flow-component-renderer-appear;
      }
    </style>
    <slot></slot>
`}static get is(){return"flow-component-renderer"}static get properties(){return{nodeid:Number,appid:String}}static get observers(){return["_attachRenderedComponentIfAble(appid, nodeid)"]}connectedCallback(){super.connectedCallback(),this._runChrome72ShadowDomBugWorkaround()}_runChrome72ShadowDomBugWorkaround(){const e=navigator.userAgent;if(e&&e.match("Chrome/")){const t=e.split("Chrome/")[1].split(".")[0];if(t&&parseInt(t)>71){const e=this._getDebouncedNotifyResizeFunction();e&&(this.style.visibility="hidden",requestAnimationFrame(()=>{this.style.visibility="",e()}))}}}_getDebouncedNotifyResizeFunction(){let e=this.parentElement;for(;;){if(!e)return;if(e.notifyResize)break;e=e.parentElement}return e._debouncedNotifyResize||(e._debouncedNotifyResize=function(){e.__debouncedNotifyResize=i.a.debounce(e.__debouncedNotifyResize,r.a,e.notifyResize)}),e._debouncedNotifyResize}ready(){super.ready(),this.addEventListener("click",(function(e){this.firstChild&&"function"==typeof this.firstChild.click&&e.target===this&&(e.stopPropagation(),this.firstChild.click())})),this.addEventListener("animationend",this._onAnimationEnd)}_asyncAttachRenderedComponentIfAble(){this._debouncer=i.a.debounce(this._debouncer,r.b,()=>this._attachRenderedComponentIfAble())}_attachRenderedComponentIfAble(){if(!this.nodeid||!this.appid)return;const e=this._getRenderedComponent();this.firstChild?e?this.firstChild!==e?(this.replaceChild(e,this.firstChild),this._defineFocusTarget(),this.onComponentRendered()):(this._defineFocusTarget(),this.onComponentRendered()):(this._clear(),this._asyncAttachRenderedComponentIfAble()):e?(this.appendChild(e),this._defineFocusTarget(),this.onComponentRendered()):this._asyncAttachRenderedComponentIfAble()}_getRenderedComponent(){try{return window.Vaadin.Flow.clients[this.appid].getByNodeId(this.nodeid)}catch(e){console.error("Could not get node %s from app %s",this.nodeid,this.appid),console.error(e)}return null}_clear(){for(;this.firstChild;)this.removeChild(this.firstChild)}onComponentRendered(){}_defineFocusTarget(){var e=this._getFirstFocusableDescendant(this.firstChild);null!==e&&e.setAttribute("focus-target","true")}_getFirstFocusableDescendant(e){if(this._isFocusable(e))return e;if(!e.children)return null;for(var t=0;t<e.children.length;t++){var n=this._getFirstFocusableDescendant(e.children[t]);if(null!==n)return n}return null}_isFocusable(e){return(!e.hasAttribute||"function"!=typeof e.hasAttribute||!e.hasAttribute("disabled")&&!e.hasAttribute("hidden"))&&0===e.tabIndex}_onAnimationEnd(e){0===e.animationName.indexOf("flow-component-renderer-appear")&&this._attachRenderedComponentIfAble()}}window.customElements.define(d.is,d);var l=n(96),u=n(135);!function(){const e=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Grid","vaadin-grid")};let t=!1;window.Vaadin.Flow.gridConnector={initLazy:n=>e((function(n){if(n.$connector)return;t||(t=!0,u.b.prototype.ensureSubCacheForScaledIndexOriginal=u.b.prototype.ensureSubCacheForScaledIndex,u.b.prototype.ensureSubCacheForScaledIndex=e((function(e){this.grid.$connector?this.itemCaches[e]||this.grid.$connector.beforeEnsureSubCacheForScaledIndex(this,e):this.ensureSubCacheForScaledIndexOriginal(e)})),u.b.prototype.isLoading=e((function(){return Boolean(f.length||Object.keys(this.pendingRequests).length||Object.keys(this.itemCaches).filter(e=>this.itemCaches[e].isLoading())[0])})),u.b.prototype.doEnsureSubCacheForScaledIndex=e((function(e){if(!this.itemCaches[e]){const t=new u.b.prototype.constructor(this.grid,this,this.items[e]);t.itemkeyCaches={},this.itemkeyCaches||(this.itemkeyCaches={}),this.itemCaches[e]=t,this.itemkeyCaches[this.grid.getItemId(t.parentItem)]=t,this.grid._loadPage(0,t)}})),u.b.prototype.getCacheAndIndexByKey=e((function(e){for(let t in this.items)if(this.grid.getItemId(this.items[t])===e)return{cache:this,scaledIndex:t};const t=Object.keys(this.itemkeyCaches);for(let n=0;n<t.length;n++){const i=t[n];let r=this.itemkeyCaches[i].getCacheAndIndexByKey(e);if(r)return r}})),u.b.prototype.getLevel=e((function(){let e=this,t=0;for(;e.parentCache;)e=e.parentCache,t++;return t})));const o={},a={},c={};let s,d,h=[],f=[];let p,m={};const g="null";m[g]=[0,0];const v=["SINGLE","NONE","MULTI"];let _={},y="SINGLE",C=!0,b=!1;n.size=0,n.itemIdPath="key",n.$connector={},n.$connector.hasEnsureSubCacheQueue=e(()=>f.length>0),n.$connector.hasParentRequestQueue=e(()=>h.length>0),n.$connector.hasRootRequestQueue=e(()=>Object.keys(o).length>0||p&&p.isActive()),n.$connector.beforeEnsureSubCacheForScaledIndex=e((function(e,t){f.push({cache:e,scaledIndex:t,itemkey:n.getItemId(e.items[t]),level:e.getLevel()}),f.sort((function(e,t){return e.scaledIndex-t.scaledIndex||e.level-t.level})),d=i.a.debounce(d,r.a,()=>{for(;f.length;)n.$connector.flushEnsureSubCache()})})),n.$connector.doSelection=e((function(e,t){"NONE"===y||!e.length||t&&n.hasAttribute("disabled")||("SINGLE"===y&&(n.selectedItems=[],_={}),n.selectedItems=n.selectedItems.concat(e),e.forEach(e=>{e&&(_[e.key]=e,t&&(e.selected=!0,n.$server.select(e.key)));const i=!n.activeItem||!e||e.key!=n.activeItem.key;!t&&"SINGLE"===y&&i&&(n.activeItem=e,n.$connector.activeItem=e)}))})),n.$connector.doDeselection=e((function(e,t){if("NONE"===y||!e.length||t&&n.hasAttribute("disabled"))return;const i=n.selectedItems.slice();for(;e.length;){const r=e.shift();for(let e=0;e<i.length;e++){const t=i[e];if(r&&r.key===t.key){i.splice(e,1);break}}r&&(delete _[r.key],t&&(delete r.selected,n.$server.deselect(r.key)))}n.selectedItems=i})),n.__activeItemChanged=e((function(e,t){"SINGLE"==y&&(e?_[e.key]||n.$connector.doSelection([e],!0):t&&_[t.key]&&(n.$connector.deselectAllowed?n.$connector.doDeselection([t],!0):n.activeItem=t))})),n._createPropertyObserver("activeItem","__activeItemChanged",!0),n.__activeItemChangedDetails=e((function(e,t){C&&(null==e&&void 0===t||(e&&!e.detailsOpened?n.$server.setDetailsVisible(e.key):n.$server.setDetailsVisible(null)))})),n._createPropertyObserver("activeItem","__activeItemChangedDetails",!0),n.$connector.setDetailsVisibleOnClick=e((function(e){C=e})),n.$connector._getPageIfSameLevel=e((function(e,t,i){let r=n._cache.getCacheAndIndex(t),o=r.cache.parentItem;return e!==(o?n.getItemId(o):g)?i:n._getPageForIndex(r.scaledIndex)})),n.$connector.getCacheByKey=e((function(e){let t=n._cache.getCacheAndIndexByKey(e);if(t)return t.cache})),n.$connector.flushEnsureSubCache=e((function(){let e=f.splice(0,1)[0],t=e.itemkey,i=n._virtualStart,r=n._virtualEnd,o=r-i,a=Math.max(0,i+n._vidxOffset-o),c=Math.min(r+n._vidxOffset+o,n._effectiveSize);for(let i=a;i<=c;i++){let r=n._cache.getItemForIndex(i);if(n.getItemId(r)===t){if(n._isExpanded(r))return e.cache.doEnsureSubCacheForScaledIndex(e.scaledIndex),!0;break}}return!1})),n.$connector.flushParentRequests=e((function(){let e=h.splice(0,20);return!!e.length&&(n.$server.setParentRequestedRanges(e),!0)})),n.$connector.beforeParentRequest=e((function(e,t,o){h.push({firstIndex:e,size:t,parentKey:o}),s=i.a.debounce(s,r.d.after(50),()=>{for(;h.length;)n.$connector.flushParentRequests()})})),n.$connector.fetchPage=e((function(e,t,i){let r=n._virtualStart,o=n._virtualEnd,a=o-r,c=Math.max(0,r+n._vidxOffset-a),s=Math.min(o+n._vidxOffset+a,n._effectiveSize),d=t,l=t;for(let e=c;e<=s;e++)d=Math.min(d,n.$connector._getPageIfSameLevel(i,e,d)),l=Math.max(l,n.$connector._getPageIfSameLevel(i,e,l));let u=Math.max(0,d),h=i!==g?l:Math.min(l,Math.floor(n.size/n.pageSize)),f=m[i];if(f||(f=[-1,-1]),f[0]!=u||f[1]!=h){f=[u,h],m[i]=f;let t=h-u+1;e(u*n.pageSize,t*n.pageSize)}})),n.dataProvider=e((function(e,t){if(e.pageSize!=n.pageSize)throw"Invalid pageSize";let s=e.page;if(e.parentItem){let i=n.getItemId(e.parentItem);a[i]||(a[i]={});let r=n.$connector.getCacheByKey(i),o=r&&r.itemkeyCaches?r.itemkeyCaches[i]:void 0;c[i]&&c[i][s]&&o?(s=Math.min(s,Math.floor(c[i].size/n.pageSize)),t(c[i][s],c[i].size)):a[i][s]=t,n.$connector.fetchPage((t,i)=>n.$connector.beforeParentRequest(t,i,e.parentItem.key),s,i)}else s=Math.min(s,Math.floor(n.size/n.pageSize)),c[g]&&c[g][s]?t(c[g][s]):o[s]=t,p=i.a.debounce(p,r.d.after(n._hasData?150:0),()=>{n.$connector.fetchPage((e,t)=>n.$server.setRequestedRange(e,t),s,g)})}));const I=e((function(e,t){void 0===t||b||n.$server.sortersChanged(n._sorters.map((function(e){return{path:e.path,direction:e.direction}})))}));n.$connector.setSorterDirections=e((function(t){b=!0,setTimeout(e(()=>{try{const e=Array.from(n.querySelectorAll("vaadin-grid-sorter"));e.forEach(e=>{t.filter(t=>t.column===e.getAttribute("path"))[0]||(e.direction=null)}),t.reverse().forEach(({column:t,direction:n})=>{e.forEach(e=>{e.getAttribute("path")===t&&e.direction!==n&&(e.direction=n)})})}finally{b=!1}}))})),n._createPropertyObserver("_previousSorters",I),n._updateItem=e((function(e,t){l.a.prototype._updateItem.call(n,e,t),e.hidden||Array.from(e.children).forEach(e=>{e._instance&&e._instance.children&&Array.from(e._instance.children).forEach(e=>{e._attachRenderedComponentIfAble&&e._attachRenderedComponentIfAble(),e.children&&Array.from(e.children).forEach(e=>{e._attachRenderedComponentIfAble&&e._attachRenderedComponentIfAble()})})})})),n._expandedInstanceChangedCallback=e((function(e,t){if(null==e.item||null==n.$server.updateExpandedState)return;let i=n.getItemId(e.item);if(n.$server.updateExpandedState(i,t),t)this.expandItem(e.item);else{delete c[i];let t=n.$connector.getCacheByKey(i);t&&t.itemkeyCaches&&t.itemkeyCaches[i]&&delete t.itemkeyCaches[i],t&&t.itemkeyCaches&&Object.keys(t.itemCaches).filter(e=>t.items[e].key===i).forEach(e=>delete t.itemCaches[e]),delete m[i],this.collapseItem(e.item)}}));const w=function(e){if(!e||!Array.isArray(e))throw"Attempted to call itemsUpdated with an invalid value: "+JSON.stringify(e);let t=Array.from(n.detailsOpenedItems),i=!1;for(let r=0;r<e.length;++r){const o=e[r];o&&(o.detailsOpened?n._getItemIndexInArray(o,t)<0&&t.push(o):n._getItemIndexInArray(o,t)>=0&&t.splice(n._getItemIndexInArray(o,t),1),_[o.key]&&(_[o.key]=o,o.selected=!0,i=!0))}n.detailsOpenedItems=t,i&&(n.selectedItems=Object.keys(_).map((function(e){return _[e]})))},$=function(e,t){let i;if((t||g)!==g){i=c[t][e];let r=n.$connector.getCacheByKey(t);if(r&&r.itemkeyCaches){let n=r.itemkeyCaches[t];const o=a[t],c=o&&o[e];E(e,i,c,n)}}else i=c[g][e],E(e,i,o[e],n._cache);return i},E=function(e,t,i,r){if(!i){let i=e*n.pageSize,o=i+n.pageSize;if(t){if(r&&r.items)for(let e=i;e<o;e++)r.items[e]&&(r.items[e]=t[e-i])}else if(r&&r.items)for(let e=i;e<o;e++)delete r.items[e]}},S=function(){n._cache.updateSize(),n._effectiveSize=n._cache.effectiveSize,n._assignModels()},k=function(e){if(!e||!n._physicalItems)return;const t=e.map(e=>e.key),i=n._physicalItems.map((e,n)=>e._item&&e._item.key&&t.indexOf(e._item.key)>-1?n:null).filter(e=>null!==e);i.length>0&&n._assignModels(i)};n.$connector.set=e((function(e,t,i){if(e%n.pageSize!=0)throw"Got new data to index "+e+" which is not aligned with the page size of "+n.pageSize;let r=i||g;const o=e/n.pageSize,a=Math.ceil(t.length/n.pageSize);for(let e=0;e<a;e++){let i=o+e,a=t.slice(e*n.pageSize,(e+1)*n.pageSize);c[r]||(c[r]={}),c[r][i]=a,n.$connector.doSelection(a.filter(e=>e.selected&&!L(e))),n.$connector.doDeselection(a.filter(e=>!e.selected&&(_[e.key]||L(e))));const s=$(i,r);s&&(w(s),k(s))}}));const x=function(e){let t=e.parentUniqueKey||g;if(c[t])for(let i in c[t])for(let r in c[t][i])if(n.getItemId(c[t][i][r])===n.getItemId(e))return{page:i,index:r,parentKey:t};return null};n.$connector.updateHierarchicalData=e((function(e){let t=[];for(let n=0;n<e.length;n++){let i=x(e[n]);if(i){c[i.parentKey][i.page][i.index]=e[n];let r=i.parentKey+":"+i.page;t[r]||(t[r]={parentKey:i.parentKey,page:i.page})}}let n=Object.keys(t);for(let e=0;e<n.length;e++){let i=t[n[e]];const r=$(i.page,i.parentKey);r&&(w(r),k(r))}})),n.$connector.updateFlatData=e((function(e){for(let t=0;t<e.length;t++){let i=x(e[t]);if(i){c[i.parentKey][i.page][i.index]=e[t];const r=parseInt(i.page)*n.pageSize+parseInt(i.index);n._cache.items[r]&&(n._cache.items[r]=e[t])}}w(e),k(e)})),n.$connector.clearExpanded=e((function(){n.expandedItems=[],f=[],h=[]})),n.$connector.clear=e((function(e,t,i){let r=i||g;if(!c[r]||0===Object.keys(c[r]).length)return;if(e%n.pageSize!=0)throw"Got cleared data for index "+e+" which is not aligned with the page size of "+n.pageSize;let o=Math.floor(e/n.pageSize),a=Math.ceil(t/n.pageSize);for(let e=0;e<a;e++){let t=o+e,a=c[r][t];n.$connector.doDeselection(a.filter(e=>_[e.key])),delete c[r][t];const s=$(t,i);s&&w(s),k(a)}let s=n._cache;if(i){const e=n._cache.getCacheAndIndexByKey(r);s=e.cache.itemCaches[e.scaledIndex]}const d=e+a*n.pageSize;for(let t=e;t<d;t++){delete s.items[t];const e=s.itemCaches[t];delete s.itemCaches[t];const n=e&&e.parentItem.key;n&&delete s.itemkeyCaches[n]}n._cache.updateSize()}));const L=function(e){const t=n.selectedItems;for(let n=0;n<t;n++){if(t[n].key===e.key)return!0}return!1};n.$connector.reset=e((function(){n.size=0,D(c),D(n._cache.items),D(m),d&&d.cancel(),s&&s.cancel(),p&&p.cancel(),d=void 0,s=void 0,f=[],h=[],S()}));const D=e=>Object.keys(e).forEach(t=>delete e[t]);n.$connector.updateSize=e=>n.size=e,n.$connector.updateUniqueItemIdPath=e=>n.itemIdPath=e,n.$connector.expandItems=e((function(e){let t=Array.from(n.expandedItems);e.filter(e=>!n._isExpanded(e)).forEach(e=>t.push(e)),n.expandedItems=t})),n.$connector.collapseItems=e((function(e){let t=Array.from(n.expandedItems);e.forEach(e=>{let i=n._getItemIndexInArray(e,t);i>=0&&t.splice(i,1)}),n.expandedItems=t,e.forEach(e=>n.$connector.removeFromQueue(e))})),n.$connector.removeFromQueue=e((function(e){let t=n.getItemId(e);delete a[t],n.$connector.removeFromArray(f,e=>e.itemkey===t),n.$connector.removeFromArray(h,e=>e.parentKey===t)})),n.$connector.removeFromArray=e((function(e,t){if(e.length)for(let n=e.length-1;n--;)t(e[n])&&e.splice(n,1)})),n.$connector.confirmParent=e((function(e,t,i){if(!a[t])return;c[t]&&(c[t].size=i);let r=Object.getOwnPropertyNames(a[t]);for(let e=0;e<r.length;e++){let n=r[e],o=m[t]||[0,0];const s=a[t][n];if(c[t]&&c[t][n]||n<o[0]||n>o[1]){delete a[t][n],s(c[t][n]||new Array(i),i)}else s&&0===i&&(delete a[t][n],s([],i))}n.$server.confirmParentUpdate(e,t),n.loading||n._assignModels()})),n.$connector.confirm=e((function(e){let t=Object.getOwnPropertyNames(o);for(let e=0;e<t.length;e++){let i=t[e],r=m[g]||[0,0];const a=n.size?Math.ceil(n.size/n.pageSize)-1:0,s=Math.min(r[1],a),d=o[i];c[g]&&c[g][i]||i<r[0]||+i>s?(delete o[i],d(c[g][i]||new Array(n.pageSize)),n._debounceIncreasePool&&n._debounceIncreasePool.flush()):d&&0===n.size&&(delete o[i],d([]))}n.$server.confirmUpdate(e)})),n.$connector.ensureHierarchy=e((function(){for(let e in c)e!==g&&delete c[e];D(m),n._cache.itemCaches={},n._cache.itemkeyCaches={},S()})),n.$connector.setSelectionMode=e((function(e){if(!(("string"==typeof e||e instanceof String)&&v.indexOf(e)>=0))throw"Attempted to set an invalid selection mode";y=e,_={}})),n.$connector.deselectAllowed=!0,n.$connector.setVerticalScrollingEnabled=e((function(e){z(n.$.table,e),n.notifyResize()}));const z=function(t,n){t.style.overflowY=n?"":"hidden",t.removeEventListener("wheel",t.__wheelListener),!n&&t.addEventListener("wheel",t.__wheelListener=e(e=>{e.deltaX?Object.defineProperty(e,"deltaY",{value:0}):e.stopImmediatePropagation()}))};function A(e,t){if(n.$connector.activeItem){e.itemKey=n.$connector.activeItem.key;const i=n.getEventContext(e);i.column&&(e.internalColumnId=i.column._flowId),n.dispatchEvent(new CustomEvent(t,{detail:e}))}}n.addEventListener("vaadin-context-menu-before-open",e((function(e){!function(e){const t=n.getEventContext(e),i=t.item&&t.item.key,r=t.column&&t.column.id;n.$server.updateContextMenuTargetItem(i,r)}(n.$contextMenuConnector.openEvent)}))),n.getContextMenuBeforeOpenDetail=e((function(e){const t=n.getEventContext(e);return{key:t.item&&t.item.key||""}})),n.addEventListener("cell-activate",e(e=>{n.$connector.activeItem=e.detail.model.item,setTimeout(()=>n.$connector.activeItem=void 0)})),n.addEventListener("click",e(e=>A(e,"item-click"))),n.addEventListener("dblclick",e(e=>A(e,"item-double-click"))),n.addEventListener("column-resize",e(e=>{n._getColumnsInOrder().filter(e=>!e.hidden).forEach(e=>{e.dispatchEvent(new CustomEvent("column-drag-resize"))}),n.dispatchEvent(new CustomEvent("column-drag-resize",{detail:{resizedColumnKey:e.detail.resizedColumn._flowId}}))})),n.addEventListener("column-reorder",e(e=>{const t=n._columnTree.slice(0).pop().filter(e=>e._flowId).sort((e,t)=>e._order-t._order).map(e=>e._flowId);n.dispatchEvent(new CustomEvent("column-reorder-all-columns",{detail:{columns:t}}))})),n.cellClassNameGenerator=e((function(e,t){const n=t.item.style;if(n)return(n.row||"")+" "+(e&&n[e._flowId]||"")})),n.dropFilter=e(e=>!e.item.dropDisabled),n.dragFilter=e(e=>!e.item.dragDisabled),n.addEventListener("grid-dragstart",e(e=>{n._isSelected(e.detail.draggedItems[0])?(n.__selectionDragData?Object.keys(n.__selectionDragData).forEach(t=>{e.detail.setDragData(t,n.__selectionDragData[t])}):(n.__dragDataTypes||[]).forEach(t=>{e.detail.setDragData(t,e.detail.draggedItems.map(e=>e.dragData[t]).join("\n"))}),n.__selectionDraggedItemsCount>1&&e.detail.setDraggedItemsCount(n.__selectionDraggedItemsCount)):(n.__dragDataTypes||[]).forEach(t=>{e.detail.setDragData(t,e.detail.draggedItems[0].dragData[t])})}))}))(n)}}();n(197);window.Vaadin.Flow.ironListConnector={initLazy:function(e){if(e.$connector)return;let t=[0,0];e.$connector={},e.$connector.placeholderItem={__placeholder:!0};const n=function(){let n=e._virtualStart,i=e._virtualEnd,r=Math.max(0,n-20),o=Math.min(i+20,e.items.length);if(t[0]!=r||t[1]!=o){t=[r,o];const n=1+o-r;e.$server.setRequestedRange(r,n)}};let o;const a=function(){o=i.a.debounce(o,r.d.after(10),n)},c=e._assignModels;e._assignModels=function(){const t=[],n=e._virtualStart,i=Math.min(e.items.length,e._physicalCount);for(let r=0;r<i;r++)void 0===e.items[n+r]&&(t.push(r),e.items[n+r]=e.$connector.placeholderItem);c.apply(e,arguments);for(let i=0;i<t.length;i++)delete e.items[n+t[i]];a()},e.items=[],e.$connector.set=function(t,n){for(let i=0;i<n.length;i++){const r=t+i;e.items[r]=n[i]}e._render()},e.$connector.updateData=function(t){const n=e.items,i={};let r=t.length;for(let e=0;e<t.length;e++){const n=t[e];i[n.key]=n}for(let t=0;t<n.length;t++){const o=i[n[t].key];if(o&&(e.items[t]=o,e.notifyPath("items."+t),r--,0==r))break}},e.$connector.clear=function(t,n){for(let i=0;i<n;i++){const n=t+i;delete e.items[n],e.notifyPath("items."+n)}},e.$connector.updateSize=function(t){const n=t-e.items.length;if(n>0)e.items.length=t,e.notifySplices("items",[{index:t-n,removed:[],addedCount:n,object:e.items,type:"splice"}]);else if(n<0){const n=e.items.slice(t,e.items.length);e.items.splice(t),e.notifySplices("items",[{index:t,removed:n,addedCount:0,object:e.items,type:"splice"}])}},e.$connector.setPlaceholderItem=function(t){t||(t={}),t.__placeholder=!0,e.$connector.placeholderItem=t}}};n(28);const h=document.createElement("template");h.innerHTML="<style>\n/* Fixes zero width in flex layouts */\niron-list {\n  flex: auto;\n  align-self: stretch;\n}\n</style>",document.head.appendChild(h.content);n(198),n(199),n(200),n(201),n(202),n(203);var f=n(66);{class e extends f.b{static get template(){return c.a`
    <template class="header" id="defaultHeaderTemplate">
      <vaadin-checkbox id="selectAllCheckbox" aria-label="Select All" hidden\$="[[selectAllHidden]]" on-click="_onSelectAllClick" checked="[[selectAll]]">
      </vaadin-checkbox>
    </template>
    <template id="defaultBodyTemplate">
      <vaadin-checkbox aria-label="Select Row" checked="[[selected]]" on-click="_onSelectClick">
      </vaadin-checkbox>
    </template>
`}static get is(){return"vaadin-grid-flow-selection-column"}static get properties(){return{autoWidth:{type:Boolean,value:!0},width:{type:String,value:"56px"},flexGrow:{type:Number,value:0},selectAll:{type:Boolean,value:!1,notify:!0},selectAllHidden:Boolean}}constructor(){super(),this._boundOnSelectEvent=this._onSelectEvent.bind(this),this._boundOnDeselectEvent=this._onDeselectEvent.bind(this)}_prepareHeaderTemplate(){return this._prepareTemplatizer(this.$.defaultHeaderTemplate)}_prepareBodyTemplate(){return this._prepareTemplatizer(this.$.defaultBodyTemplate)}connectedCallback(){super.connectedCallback(),this._grid&&(this._grid.addEventListener("select",this._boundOnSelectEvent),this._grid.addEventListener("deselect",this._boundOnDeselectEvent))}disconnectedCallback(){if(super.disconnectedCallback(),this._grid){this._grid.removeEventListener("select",this._boundOnSelectEvent),this._grid.removeEventListener("deselect",this._boundOnDeselectEvent);if(/^((?!chrome|android).)*safari/i.test(navigator.userAgent)&&window.ShadyDOM&&this.parentElement){const e=this.parentElement,t=this.nextElementSibling;e.removeChild(this),t?e.insertBefore(this,t):e.appendChild(this)}}}_onSelectClick(e){e.target.checked?this._grid.$connector.doDeselection([e.model.item],!0):this._grid.$connector.doSelection([e.model.item],!0),e.target.checked=!e.target.checked}_onSelectAllClick(e){e.preventDefault(),this._grid.hasAttribute("disabled")?e.target.checked=!e.target.checked:this.selectAll?this.$server.deselectAll():this.$server.selectAll()}_onSelectEvent(e){}_onDeselectEvent(e){e.detail.userOriginated&&(this.selectAll=!1)}}customElements.define(e.is,e),Vaadin.GridFlowSelectionColumnElement=e}n(228),n(229),n(141),n(205),n(242),n(144),n(243),n(244),n(111),n(184),n(255),n(185),n(223),n(173),n(225),n(208),n(112),n(221),n(232),n(209),n(138),n(186),n(245),n(156),n(76),n(83),n(177),n(233),n(222),n(224),n(226),n(246),n(188),n(189),n(247),n(227),n(187),n(248),n(256),n(190),n(113),n(216),n(176),n(170),n(249);const p=function(e,t=!1){const n=document.createElement("template");n.innerHTML=e,document.head[t?"insertBefore":"appendChild"](n.content,document.head.firstChild)}}}]);