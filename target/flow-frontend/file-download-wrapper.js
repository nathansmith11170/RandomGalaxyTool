import '@polymer/polymer/polymer-legacy.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';
import { PolymerElement } from '@polymer/polymer/polymer-element.js';
class FileDownloadWrapper extends PolymerElement {
  static get template() {
    return html`
   <a id="download-link"></a> 
`;
  }

  static get is() {
      return 'file-download-wrapper';
  }

  static get properties() {
      return {
          // Declare your properties here.
      };
  }
}
customElements.define(FileDownloadWrapper.is, FileDownloadWrapper);

