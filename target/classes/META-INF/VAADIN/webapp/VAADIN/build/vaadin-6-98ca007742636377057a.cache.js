(window.webpackJsonp=window.webpackJsonp||[]).push([[6],{267:function(e,t,s){"use strict";s.r(t),s.d(t,"addCssBlock",(function(){return B}));s(24);var a=s(47);s(28);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
const i=a.a`
  <custom-style>
    <style>
      html {
        --vaadin-user-color-0: #df0b92;
        --vaadin-user-color-1: #650acc;
        --vaadin-user-color-2: #097faa;
        --vaadin-user-color-3: #ad6200;
        --vaadin-user-color-4: #bf16f3;
        --vaadin-user-color-5: #084391;
        --vaadin-user-color-6: #078836;
      }

      [theme~="dark"] {
        --vaadin-user-color-0: #ff66c7;
        --vaadin-user-color-1: #9d8aff;
        --vaadin-user-color-2: #8aff66;
        --vaadin-user-color-3: #ffbd66;
        --vaadin-user-color-4: #dc6bff;
        --vaadin-user-color-5: #66fffa;
        --vaadin-user-color-6: #e6ff66;
      }
    </style>
  </custom-style>
`;document.head.appendChild(i.content);var r=s(3);s(21),s(26),s(27),s(22);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
Object(r.b)("vaadin-user-tags-overlay",r.a`
    [part='overlay'] {
      will-change: opacity, transform;
    }

    :host([opening]) [part='overlay'] {
      animation: 0.1s lumo-user-tags-enter ease-out both;
    }

    @keyframes lumo-user-tags-enter {
      0% {
        opacity: 0;
      }
    }

    :host([closing]) [part='overlay'] {
      animation: 0.1s lumo-user-tags-exit both;
    }

    @keyframes lumo-user-tags-exit {
      100% {
        opacity: 0;
      }
    }
  `,{moduleId:"lumo-user-tags-overlay"}),Object(r.b)("vaadin-user-tag",r.a`
    :host {
      font-family: var(--lumo-font-family);
      font-size: var(--lumo-font-size-xxs);
      border-radius: var(--lumo-border-radius-s);
      box-shadow: var(--lumo-box-shadow-xs);
      --vaadin-user-tag-offset: var(--lumo-space-xs);
    }

    [part='name'] {
      color: var(--lumo-primary-contrast-color);
      padding: 0.3em calc(0.3em + var(--lumo-border-radius-s) / 4);
      line-height: 1;
      font-weight: 500;
      min-width: calc(var(--lumo-line-height-xs) * 1em + 0.45em);
    }
  `,{moduleId:"lumo-user-tag"});var o=s(33),n=s(37),d=s(36),l=s(84),h=s(49),c=s(34),u=s(193);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
const p=window.ShadyCSS&&!window.ShadyCSS.nativeCss,v=window.ShadyDOM&&window.ShadyDOM.inUse,g=(e,t,s)=>{p?window.ShadyCSS.styleSubtree(e,{[t]:s}):e.style.setProperty(t,s)};let m={};
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
class f extends(Object(c.a)(Object(h.a)(o.a))){static get is(){return"vaadin-user-tag"}static get template(){return o.b`
      <style>
        :host {
          display: block;
          box-sizing: border-box;
          margin: 0 0 var(--vaadin-user-tag-offset);
          opacity: 0;
          height: 1.3rem;
          transition: opacity 0.2s ease-in-out;
          background-color: var(--vaadin-user-tag-color);
          color: #fff;
          cursor: default;
          -webkit-user-select: none;
          user-select: none;
          --vaadin-user-tag-offset: 4px;
        }

        :host(.show) {
          opacity: 1;
        }

        :host(:last-of-type) {
          margin-bottom: 0;
        }

        [part='name'] {
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
          box-sizing: border-box;
          padding: 2px 4px;
          height: 1.3rem;
          font-size: 13px;
        }
      </style>
      <!-- TODO: image / avatar -->
      <div part="name">[[name]]</div>
    `}static get properties(){return{name:{type:String},uid:{type:String},colorIndex:{type:Number,observer:"_colorIndexChanged"}}}ready(){super.ready(),this.addEventListener("mousedown",this._onClick.bind(this),!0)}_colorIndexChanged(e){null!=e&&g(this,"--vaadin-user-tag-color",`var(--vaadin-user-color-${e})`)}_onClick(e){e.preventDefault(),this.dispatchEvent(new CustomEvent("user-tag-click",{bubbles:!0,composed:!0,detail:{name:this.name}}))}}customElements.define(f.is,f);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
const b=(e,t)=>new Promise(s=>{const a=()=>{e.removeEventListener(t,a),s()};e.addEventListener(t,a)});class y extends u.a{static get is(){return"vaadin-user-tags-overlay"}ready(){super.ready(),this.$.overlay.setAttribute("tabindex","-1")}}customElements.define(y.is,y),Object(r.b)("vaadin-user-tags-overlay",r.a`
    :host {
      align-items: stretch;
      justify-content: flex-start;
      background: transparent;
      box-shadow: none;
      bottom: auto;
    }

    [part='overlay'] {
      box-shadow: none;
      background: transparent;
      position: relative;
      left: -4px;
      padding: 4px;
      outline: none;
      overflow: visible;
    }

    :host([dir='rtl']) [part='overlay'] {
      left: auto;
      right: -4px;
    }

    [part='content'] {
      padding: 0;
    }

    :host([dir='rtl']) {
      left: auto;
    }

    :host(:not([dir='rtl'])) {
      right: auto;
    }

    :host([opening]),
    :host([closing]) {
      animation: 0.14s user-tags-overlay-dummy-animation;
    }

    @keyframes user-tags-overlay-dummy-animation {
      0% {
        opacity: 1;
      }
      100% {
        opacity: 1;
      }
    }
  `);class w extends(Object(c.a)(Object(h.a)(o.a))){static get is(){return"vaadin-user-tags"}static get template(){return o.b`
      <style>
        :host {
          position: absolute;
        }

        [part='tags'] {
          display: flex;
          flex-direction: column;
          align-items: flex-start;
        }
      </style>
      <vaadin-user-tags-overlay modeless id="overlay" opened="[[opened]]" on-vaadin-overlay-open="_onOverlayOpen">
        <template>
          <div part="tags"></div>
        </template>
      </vaadin-user-tags-overlay>
    `}static get properties(){return{hasFocus:{type:Boolean,value:!1,observer:"_hasFocusChanged"},opened:{type:Boolean,value:!1,observer:"_openedChanged"},flashing:{type:Boolean,value:!1},target:{type:Object},users:{type:Array,value:()=>[]},_flashQueue:{type:Array,value:()=>[]}}}constructor(){super(),this._boundSetPosition=this._debounceSetPosition.bind(this)}connectedCallback(){super.connectedCallback(),window.addEventListener("resize",this._boundSetPosition),window.addEventListener("scroll",this._boundSetPosition)}disconnectedCallback(){super.disconnectedCallback(),window.removeEventListener("resize",this._boundSetPosition),window.removeEventListener("scroll",this._boundSetPosition)}_debounceSetPosition(){this._debouncePosition=n.a.debounce(this._debouncePosition,d.d.after(16),()=>this._setPosition())}_openedChanged(e){e&&this._setPosition()}_hasFocusChanged(e){e&&this.flashing&&this.stopFlash()}_setPosition(){if(!this.opened)return;const e=this.target.getBoundingClientRect(),t=this.$.overlay.getBoundingClientRect();this._translateX="rtl"===this.getAttribute("dir")?e.right-t.right+(this._translateX||0):e.left-t.left+(this._translateX||0),this._translateY=e.top-t.top+(this._translateY||0)+e.height;const s=window.devicePixelRatio||1;this._translateX=Math.round(this._translateX*s)/s,this._translateY=Math.round(this._translateY*s)/s,this.$.overlay.style.transform=`translate3d(${this._translateX}px, ${this._translateY}px, 0)`}get wrapper(){return this.$.overlay.content.querySelector('[part="tags"]')}createUserTag(e){const t=document.createElement("vaadin-user-tag");return t.name=e.name,t.uid=e.id,t.colorIndex=e.colorIndex,t}getTagForUser(e){return Array.from(this.wrapper.children).filter(t=>t.uid===e.id)[0]}getChangedTags(e,t){const s=t.map(e=>this.getTagForUser(e));return{added:e.map(e=>this.getTagForUser(e)||this.createUserTag(e)),removed:s}}getChangedUsers(e,t){const s=[],a=[];t.forEach(t=>{for(let e=0;e<t.removed.length;e++)a.push(t.removed[e]);for(let a=t.addedCount-1;a>=0;a--)s.push(e[t.index+a])});return{addedUsers:s.filter(e=>!a.some(t=>e.id===t.id)),removedUsers:a.filter(e=>!s.some(t=>e.id===t.id))}}applyTagsStart({added:e,removed:t}){const s=this.wrapper;t.forEach(e=>{e&&(e.classList.add("removing"),e.classList.remove("show"))}),e.forEach(e=>s.insertBefore(e,s.firstChild))}applyTagsEnd({added:e,removed:t}){const s=this.wrapper;t.forEach(e=>{e&&e.parentNode===s&&s.removeChild(e)}),e.forEach(e=>e&&e.classList.add("show"))}setUsers(e){this.render();const t=Object(l.a)(e,this.users);if(0===t.length)return;const{addedUsers:s,removedUsers:a}=this.getChangedUsers(e,t);if(0===s.length&&0===a.length)return;const i=this.getChangedTags(s,a);if(this._flashQueue.length>0)for(let e=0;e<a.length;e++)if(null===i.removed[e])for(let t=0;t<this._flashQueue.length;t++)this._flashQueue[t].some(t=>t.uid===a[e].id)&&this.splice("_flashQueue",e,1);if(this.opened&&this.hasFocus)this.updateTags(e,i);else if(s.length&&"hidden"!==document.visibilityState){const t=i.added;this.flashing?this.push("_flashQueue",t):this.flashTags(t),this.set("users",e)}else this.updateTagsSync(e,i)}_onOverlayOpen(){Array.from(this.wrapper.children).forEach(e=>{e.classList.contains("removing")||e.classList.add("show")})}flashTags(e){this.flashing=!0;const t=this.wrapper,s=Array.from(t.children);s.forEach(e=>e.style.display="none"),e.forEach(e=>{t.insertBefore(e,t.firstChild)}),this.flashPromise=new Promise(t=>{b(this.$.overlay,"vaadin-overlay-open").then(()=>{this._debounceFlashStart=n.a.debounce(this._debounceFlashStart,d.d.after(2200),()=>{this.hasFocus||e.forEach(e=>e.classList.remove("show")),this._debounceFlashEnd=n.a.debounce(this._debounceFlashEnd,d.d.after(200),()=>{const e=()=>{s.forEach(e=>e.style.display="block"),this.flashing=!1,t()};this.hasFocus?e():(b(this.$.overlay,"animationend").then(()=>{e()}),this.opened=!1)})})})}).then(()=>{if(this._flashQueue.length>0){const e=this._flashQueue[0];this.splice("_flashQueue",0,1),this.flashTags(e)}}),this.opened=!0}stopFlash(){this._debounceFlashStart&&this._debounceFlashStart.flush(),this._debounceFlashEnd&&this._debounceFlashEnd.flush(),this.$.overlay._flushAnimation("closing")}updateTags(e,t){this.applyTagsStart(t),this._debounceRender=n.a.debounce(this._debounceRender,d.d.after(200),()=>{this.set("users",e),this.applyTagsEnd(t),0===e.length&&this.opened&&(this.opened=!1)})}updateTagsSync(e,t){this.applyTagsStart(t),this.set("users",e),this.applyTagsEnd(t)}show(){this.hasFocus=!0,this.opened=!0}hide(){this.hasFocus=!1,this.opened=!1}render(){this._debounceRender&&this._debounceRender.isActive()&&this._debounceRender.flush()}}customElements.define(w.is,w),
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
Object(r.b)("vaadin-field-outline",r.a`
    :host {
      transition: opacity 0.3s;
    }

    :host::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      box-shadow: 0 0 0 2px var(--_active-user-color);
      border-radius: var(--lumo-border-radius);
      transition: box-shadow 0.3s;
    }

    :host([context='vaadin-checkbox'])::before {
      box-shadow: 0 0 0 2px var(--lumo-base-color), 0 0 0 4px var(--_active-user-color);
    }

    :host([context='vaadin-radio-button'])::before {
      border-radius: 50%;
      box-shadow: 0 0 0 3px var(--lumo-base-color), 0 0 0 5px var(--_active-user-color);
    }

    :host([context='vaadin-item'])::before {
      box-shadow: inset 0 0 0 2px var(--_active-user-color);
    }

    :host([context='vaadin-text-area']) {
      display: none;
    }
  `,{moduleId:"lumo-field-outline"}),Object(r.b)("vaadin-text-area",r.a`
    :host([has-highlighter]) [part="input-field"] {
      box-shadow: 0 0 0 2px var(--_active-user-color);
    }
  `,{moduleId:"lumo-text-area-outline"});var _=s(87);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/const x=new WeakMap,F=e=>{if(!x.has(e)){const t=(e=>{switch(e.tagName.toLowerCase()){case"vaadin-combo-box":case"vaadin-date-picker":case"vaadin-date-time-picker-date-picker":case"vaadin-date-time-picker-time-picker":case"vaadin-select":case"vaadin-time-picker":return e.focusElement;default:return e}})(e),s=(e=>{switch(e.tagName.toLowerCase()){case"vaadin-text-area":case"vaadin-text-field":case"vaadin-password-field":case"vaadin-email-field":case"vaadin-number-field":case"vaadin-integer-field":case"vaadin-big-decimal-field":case"vaadin-select-text-field":case"vaadin-date-picker-text-field":case"vaadin-time-picker-text-field":case"vaadin-date-time-picker-date-text-field":case"vaadin-date-time-picker-time-text-field":return e.shadowRoot.querySelector('[part="input-field"]');case"vaadin-checkbox":return e.shadowRoot.querySelector('[part="checkbox"]');case"vaadin-radio-button":return e.shadowRoot.querySelector('[part="radio"]');default:return e}})(t);s.style.position="relative";((e,t)=>{if(v){const s=e.tagName.toLowerCase();if(!m[s]){const e=document.createElement("style"),a=t.replace(/:host\((.+)\)/,s+"$1");e.textContent=a,e.setAttribute("scope",s),m[s]=e,document.head.appendChild(e)}}else{const s=document.createElement("style");s.textContent=t,e.shadowRoot.appendChild(s)}})(t,'\n      :host([active]) [part="outline"],\n      :host([focus-ring]) [part="outline"] {\n        display: none;\n      }\n    ');const a=document.createElement("vaadin-field-outline");(s===e?e.shadowRoot:s).appendChild(a),a.setAttribute("context",t.tagName.toLowerCase()),x.set(e,{root:t,target:s,outline:a})}return x.get(e)};
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
class O{constructor(e){this.component=e,this.initTags(e)}getFields(){return[this.component]}getFieldIndex(e){return this.getFields().indexOf(e)}getFocusTarget(e){return this.component}initTags(e){const t=document.createElement("vaadin-user-tags");e.shadowRoot.appendChild(t),t.target=e,this._tags=t,e.addEventListener("mouseenter",e=>{e.relatedTarget!==this._tags.$.overlay&&(this._mouse=!0,this._mouseDebouncer=n.a.debounce(this._mouseDebouncer,d.d.after(200),()=>{this._mouse&&this._tags.show()}))}),e.addEventListener("mouseleave",e=>{e.relatedTarget!==this._tags.$.overlay&&(this._mouse=!1,this._hasFocus||this._tags.hide())}),e.addEventListener("vaadin-highlight-show",e=>{this._hasFocus=!0,this._debouncer&&this._debouncer.isActive()?this._debouncer.cancel():this._tags.show()}),e.addEventListener("vaadin-highlight-hide",e=>{this._hasFocus=!1,this._mouse||(this._debouncer=n.a.debounce(this._debouncer,d.d.after(1),()=>{this._tags.hide()}))}),this._tags.$.overlay.addEventListener("mouseleave",t=>{t.relatedTarget!==e&&(this._mouse=!1,e.hasAttribute("focused")||this._tags.hide())})}setOutlines(e){const t=this.getFields();t.forEach((s,a)=>{const{outline:i}=F(s),r=1===t.length?0:e.map(e=>e.fieldIndex).indexOf(a);i.user=e[r]})}showOutline(e){this.fire("show",e)}hideOutline(e){this.fire("hide",e)}fire(e,t){this.component.dispatchEvent(new CustomEvent("vaadin-highlight-"+e,{bubbles:!0,composed:!0,detail:{fieldIndex:this.getFieldIndex(t)}}))}redraw(e){this._tags.setUsers(e),this.setOutlines(e)}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class k extends O{constructor(e){super(e),this.datePicker=e,this.fullscreenFocus=!1,this.blurWhileOpened=!1,this.addListeners(e)}addListeners(e){this.overlay=e.$.overlay,e.addEventListener("focus",e=>this.onFocus(e),!0),e.addEventListener("opened-changed",e=>this.onOpenedChanged(e)),this.overlay.addEventListener("focusout",e=>this.onOverlayFocusOut(e)),e.addEventListener("focusin",e=>this.onFocusIn(e)),e.addEventListener("focusout",e=>this.onFocusOut(e))}onFocus(e){const t=this.datePicker;t._fullscreen&&e.relatedTarget!==this.overlay&&(this.fullscreenFocus=!0,t.opened&&(this.fullscreenFocus=!1,this.showOutline(t)))}onFocusIn(e){e.relatedTarget!==this.overlay&&(this.blurWhileOpened?this.blurWhileOpened=!1:this.showOutline(this.datePicker))}onFocusOut(e){this.fullscreenFocus||e.relatedTarget===this.overlay||(this.datePicker.opened?this.blurWhileOpened=!0:this.hideOutline(this.datePicker))}onOverlayFocusOut(e){e.relatedTarget!==this.datePicker&&(this.blurWhileOpened=!0)}onOpenedChanged(e){if(!1===e.detail.value&&this.blurWhileOpened)return this.blurWhileOpened=!1,void this.hideOutline(this.datePicker)}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class E extends O{constructor(e){super(e),this.addListeners(e)}addListeners(e){e.addEventListener("focusin",e=>this.onFocusIn(e)),e.addEventListener("focusout",e=>this.onFocusOut(e))}onFocusIn(e){const t=this.getFocusTarget(e);this.showOutline(t)}onFocusOut(e){const t=this.getFocusTarget(e);this.hideOutline(t)}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class C extends k{constructor(e,t){super(e),this.component=t}getFieldIndex(){return 0}}class T extends E{constructor(e,t){super(e),this.component=t,this.timePicker=e}getFocusTarget(e){return this.timePicker}getFieldIndex(){return 1}}class L extends O{constructor(e){super(e);const[t,s]=this.getFields();this.dateObserver=new C(t,e),this.timeObserver=new T(s,e)}getFields(){return this.component.$.customField.inputs}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class S extends E{getFields(){return this.component._checkboxes}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(e=>-1!==t.indexOf(e))[0]}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class A extends E{getFields(){return this.component._radioButtons}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(e=>-1!==t.indexOf(e))[0]}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class P extends E{constructor(e){super(e),this.blurWhileOpened=!1,this.overlay=e._overlayElement}addListeners(e){super.addListeners(e),e.addEventListener("opened-changed",t=>{e._phone&&!1===t.detail.value&&this.hideOutline(e)})}onFocusIn(e){this.overlay.contains(e.relatedTarget)||!this.component._phone&&this.overlay.hasAttribute("closing")||super.onFocusIn(e)}onFocusOut(e){this.overlay.contains(e.relatedTarget)||super.onFocusOut(e)}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class U extends E{getFields(){return this.component.items||[]}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(e=>-1!==t.indexOf(e))[0]}}
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/class I extends(Object(c.a)(Object(h.a)(o.a))){static get is(){return"vaadin-field-outline"}static get template(){return o.b`
      <style>
        :host {
          display: block;
          box-sizing: border-box;
          position: absolute;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          width: 100%;
          height: 100%;
          pointer-events: none;
          user-select: none;
          opacity: 0;
          --_active-user-color: transparent;
        }

        :host([has-active-user]) {
          opacity: 1;
        }
      </style>
    `}static get properties(){return{user:{type:Object,value:null,observer:"_userChanged"}}}ready(){super.ready(),this.setAttribute("part","outline"),this._field=this.getRootNode().host}_userChanged(e){const t="--_active-user-color";if(e){this.setAttribute("has-active-user","");const s=`var(--vaadin-user-color-${e.colorIndex})`;g(this,t,s),this._field&&g(this._field,t,s)}else this.removeAttribute("has-active-user"),g(this,t,"transparent"),this._field&&g(this._field,t,"transparent")}}customElements.define(I.is,I);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
const $=new WeakMap;class j extends(Object(c.a)(Object(h.a)(o.a))){static init(e){if(!$.has(e)){const t=document.createElement(this.is);t._field=e,e.setAttribute("has-highlighter",""),window.ShadyDOM&&window.ShadyDOM.flush(),$.set(e,t),t.observer=this.initFieldObserver(e),e.shadowRoot.appendChild(t)}return $.get(e)}static initFieldObserver(e){let t;switch(e.tagName.toLowerCase()){case"vaadin-date-picker":t=new k(e);break;case"vaadin-date-time-picker":t=new L(e);break;case"vaadin-select":t=new P(e);break;case"vaadin-checkbox-group":t=new S(e);break;case"vaadin-radio-group":t=new A(e);break;case"vaadin-list-box":t=new U(e);break;default:t=new E(e)}return t}static addUser(e,t){this.init(e).addUser(t)}static removeUser(e,t){this.init(e).removeUser(t)}static setUsers(e,t){this.init(e).setUsers(t)}static get is(){return"vaadin-field-highlighter"}static get template(){return o.b`
      <style>
        :host {
          display: none;
        }
      </style>
    `}static get properties(){return{user:{type:Object,value:null,observer:"_userChanged"},users:{type:Array,value:()=>[]}}}ready(){super.ready(),this.redraw(),_.a.requestAvailability()}addUser(e){e&&(this.push("users",e),this.redraw(),this.user=e)}setUsers(e){Array.isArray(e)&&(this.set("users",e),this.redraw(),this.user=e[e.length-1]||null)}removeUser(e){if(e&&void 0!==e.id){let t;for(let s=0;s<this.users.length;s++)if(this.users[s].id===e.id){t=s;break}void 0!==t&&(this.splice("users",t,1),this.redraw(),this.users.length>0?this.user=this.users[this.users.length-1]:this.user=null)}}redraw(){this.observer.redraw(Array.from(this.users).reverse())}_announce(e){const t=this._field.label||"";this.dispatchEvent(new CustomEvent("iron-announce",{bubbles:!0,composed:!0,detail:{text:t?`${e} ${t}`:e}}))}_userChanged(e){e&&this._announce("root started editing")}}customElements.define(j.is,j);
/**
@license
Copyright (C) 2020 Vaadin Ltd
This program is available under Commercial Vaadin Runtime License 1.0 (CVRLv1).
For the full License, see http://vaadin.com/license/cvrl-1
*/
s(56);class R extends o.a{static get template(){return a.a`
   <a id="download-link"></a> 
`}static get is(){return"file-download-wrapper"}static get properties(){return{}}}customElements.define(R.is,R);s(231),s(235),s(69),s(88),s(180),s(236),s(181),s(253),s(254),s(39),s(237),s(238),s(239),s(240),s(182),s(241),s(52);const B=function(e,t=!1){const s=document.createElement("template");s.innerHTML=e,document.head[t?"insertBefore":"appendChild"](s.content,document.head.firstChild)};let W;document.documentElement.setAttribute("theme","dark");const M=document.getElementsByTagName("script");for(let e=0;e<M.length;e++){const t=M[e];if("module"==t.getAttribute("type")&&t.getAttribute("data-app-id")&&!t["vaadin-bundle"]){W=t;break}}if(!W)throw new Error("Could not find the bundle script to identify the application id");W["vaadin-bundle"]=!0,window.Vaadin.Flow.fallbacks||(window.Vaadin.Flow.fallbacks={});const N=window.Vaadin.Flow.fallbacks;N[W.getAttribute("data-app-id")]={},N[W.getAttribute("data-app-id")].loadFallback=function(){return Promise.all([s.e(1),s.e(3)]).then(s.bind(null,268))}}}]);