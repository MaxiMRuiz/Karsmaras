(function ($) {
	'use strict';

	var Paginator = function () {
		return {
			// Attributes
			obj: null,
			options: null,
			nav: null,

			// Methods
			build: function (obj, opts) {
				this.obj = obj;
				this.options = opts;

				if (!this.options.optional || this._totalRows() > this.options.limit) {
					this._createNavigation();
					this._setPage();
				}

				if (this.options.onCreate) this.options.onCreate(obj);

				return this.obj;
			},

			_createNavigation: function () {
				this._createNavigationWrapper();
				this._createNavigationButtons();
				this._appendNavigation();
				this._addNavigationCallbacks();
			},
			_createNavigationWrapper: function () {
				this.nav = $('<div>', {
					class: this.options.navigationClass,
					align: "center"
				});
			},
			_createNavigationButtons: function () {
				// Add 'first' button
				if (this.options.first) {
					this._createNavigationButton(this.options.firstText, {
						'data-first': true,
						'onclick': "filter()"
					});
				}

				// Add 'previous' button
				if (this.options.previous) {
					this._createNavigationButton(this.options.previousText, {
						'data-direction': -1,
						'data-previous': true,
						'onclick': "filter()"
					});
				}

				// Add page buttons
				for (var i = 0; i < this._totalPages(); ++i) {
					this._createNavigationButton(this.options.pageToText(i), {
						'data-page': i,
						'onclick': "filter()"
					});
				}

				// Add 'next' button
				if (this.options.next) {
					this._createNavigationButton(this.options.nextText, {
						'data-direction': 1,
						'data-next': true,
						'onclick': "filter()"
					});
				}

				// Add 'last' button
				if (this.options.last) {
					this._createNavigationButton(this.options.lastText, {
						'data-last': true,
						'onclick': "filter()"
					});
				}
			},
			_createNavigationButton: function (text, options) {
				this.nav.append($('<a>', $.extend(options, {
					href: '#',
					text: text,
					class: 'btn btn-dark paginate-btn',
					role: 'button'
				})));

			},
			// _createNavigationText: function (current, options) {
			// 	this.nav.append($('<strong>', $.extend(options, {
			// 		text: ' Página ' + current + "/" + this._totalPages(),
			// 		class: 'paginate-btn'
			// 	})));
			// },

			_appendNavigation: function () {
				// Add the content to the navigation block
				if (this.options.navigationWrapper) this.options.navigationWrapper.append(this.nav);
				// Add it after the table
				else this.obj.before(this.nav);
			},
			_addNavigationCallbacks: function () {
				var paginator = this;

				paginator.nav.find('a').click(function (e) {
					var direction = $(this).data('direction') * 1;

					// 'First' button
					if ($(this).data('first') !== undefined) {
						paginator._setPage(0);
					}
					// Page button
					else if ($(this).data('page') !== undefined) {
						paginator._setPage($(this).data('page') * 1);
					}
					// 'Previous' or 'Next' button
					else if ($(this).data('previous') !== undefined || $(this).data('next') !== undefined) {
						var page = paginator._currentPage() + direction;
						if (page >= 0 && page <= paginator._totalPages() - 1) {
							paginator._setPage(page);
						} else {
							paginator._setPage(paginator._currentPage());
						}
					}
					// 'Last' button
					else if ($(this).data('last') !== undefined) {
						paginator._setPage(paginator._totalPages() - 1);
					}

					// Handle callback
					if (paginator.options.onSelect) paginator.options.onSelect(paginator.obj, paginator._currentPage());
					e.preventDefault();
					return false;
				});
			},

			_setPage: function (index) {
				if (index == undefined)
					index = this.options.initialPage;
				var i;
				var totalPages = this._totalPages();
				this.nav.find('strong').remove();
				// this._createNavigationText(index + 1);

				// Hide all elements, and then show the current page.
				this._rows().hide().slice(index * this.options.limit, (index + 1) * this.options.limit).show();

				// Set the current button as active
				this.nav.find('a').removeAttr('data-selected').siblings('a[data-page=' + index + ']')
					.attr('data-selected', true);
				//show only 5 buttons
				//first 3 buttons
				if (totalPages > 5 && index < 3) {
					for (i = 0; i < 5; i++)
						this.nav.find('a[data-page=' + i + ']').show();
					for (i = 5; i < totalPages; i++)
						this.nav.find('a[data-page=' + i + ']').hide();
				}
				//last 3 buttons
				if (totalPages > 5 && index > totalPages - 3) {
					for (i = totalPages; i > totalPages - 5; i--)
						this.nav.find('a[data-page=' + i + ']').show();
					for (i = 0; i < totalPages - 5; i++)
						this.nav.find('a[data-page=' + i + ']').hide();
				}
				// intermediate buttons
				if (totalPages > 5 && index >= 3 && index <= totalPages - 3) {
					for (i = index - 2; i <= index + 2; i++)
						this.nav.find('a[data-page=' + i + ']').show();
					for (i = 0; i < index - 2; i++)
						this.nav.find('a[data-page=' + i + ']').hide();
					for (i = index + 3; i < totalPages; i++)
						this.nav.find('a[data-page=' + i + ']').hide();
				}
			},

			_currentPage: function () {
				return this.nav.find('a[data-selected=true]').data('page');
			},
			_totalRows: function () {
				// Count the total rows of the selector
				return this._rows().length;
			},
			_rows: function () {
				if (this.options.filter)
					return this.obj.find(this.options.childrenSelector).filter(function () {
						return $(this).css('display') == "table-row";
					});
				else
					return this.obj.find(this.options.childrenSelector);
			},
			_totalPages: function () {
				return Math.ceil(this._totalRows() / this.options.limit);
			}
		};
	};

	$.fn.paginate = function (options) {

		return Paginator().build(this, $.extend({}, $.fn.paginate.defaults, options));

	};

	$.fn.paginate.defaults = {
		limit: 4,
		initialPage: 0,

		previous: true,
		previousText: '<',
		next: true,
		nextText: '>',
		first: true,
		firstText: '<<',
		last: true,
		lastText: '>>',

		optional: true,
		align: 'right',
		onCreate: null,
		onSelect: null,
		filter: true,

		childrenSelector: "tbody > tr",
		navigationWrapper: null,
		navigationClass: "page-navigation mb-1",
		pageToText: function (i) {
			return (i + 1).toString();
		}
	}

}(jQuery));