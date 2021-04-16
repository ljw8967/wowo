var dzrevapi;
var dzQuery =jQuery;
function dz_rev_slider_1(){
	if(dzQuery("#rev_slider_1061_1").revolution == undefined){
		revslider_showDoubleJqueryError("#rev_slider_1061_1");
	}else{
	dzrevapi = dzQuery("#rev_slider_1061_1").show().revolution({
	sliderType:"standard",
	jsFileLocation:"revolution/js/",
	sliderLayout:"fullscreen",
	dottedOverlay:"none",
	delay:9000,
	navigation: {
		keyboardNavigation:"off",
		keyboard_direction: "horizontal",
		mouseScrollNavigation:"off",
		mouseScrollReverse:"default",
		onHoverStop:"off",
		touch:{
			touchenabled:"on",
			swipe_threshold: 75,
			swipe_min_touches: 50,
			swipe_direction: "horizontal",
			drag_block_vertical: false
		}
		,
		thumbnails: {
			style: "hesperiden",
			enable: true,
			width: 165,
			height: 110,
			min_width: 100,
			wrapper_padding: 3,
			wrapper_color: "#ffffff",
			wrapper_opacity: "0.5",
			tmp: '<span class="tp-thumb-image"></span><span class="tp-thumb-title">{{title}}</span>',
			visibleAmount: 5,
			hide_onmobile: false,
			hide_onleave: false,
			direction: "horizontal",
			span: false,
			position: "inner",
			space: 3,
			h_align: "center",
			v_align: "bottom",
			h_offset: 0,
			v_offset: 50
		}
	},
	responsiveLevels:[1240,1024,778,480],
	visibilityLevels:[1240,1024,778,480],
	gridwidth:[1240,1024,778,480],
	gridheight:[868,768,960,720],
	lazyType:"none",
	parallax: {
		type:"3D",
		origo:"slidercenter",
		speed:1000,
		levels:[2,4,6,8,10,12,14,16,45,50,47,48,49,50,0,50],
		type:"3D",
		ddd_shadow:"off",
		ddd_bgfreeze:"on",
		ddd_overflow:"hidden",
		ddd_layer_overflow:"visible",
		ddd_z_correction:100,
	},
	spinner:"off",
	stopLoop:"on",
	stopAfterLoops:0,
	stopAtSlide:1,
	shuffle:"off",
	autoHeight:"off",
	fullScreenAutoWidth:"off",
	fullScreenAlignForce:"off",
	fullScreenOffsetContainer: "",
	fullScreenOffset: "0",
	disableProgressBar:"on",
	hideThumbsOnMobile:"off",
	hideSliderAtLimit:0,
	hideCaptionAtLimit:0,
	hideAllCaptionAtLilmit:0,
	debugMode:false,
	fallbacks: {
		simplifyAll:"off",
		nextSlideOnWindowFocus:"off",
		disableFocusListener:false,
	}
	});
	}
}