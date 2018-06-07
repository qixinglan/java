(function ibmCoreAuto(){

var ibmCMURLPathname = window.location.pathname;

var ibmCMURLMatch = {
'/manage/trial/apim.html':'IBM_Commerce_TopLevel',
'/manage/trial/webpush.html':'IBM_Commerce_MarketingCategory',
'/commerce/us-en':'IBM_Commerce_eCommerce',
'/middleware/application-platform/en-us/index.html':'IBM_Systems_Middleware_ApplicationPlatform',
'/middleware/en-us':'IBM_Systems_Middleware',
'/middleware/integration/en-us':'IBM_Systems_Middleware_Integration',
'/middleware/integration/en-us/api-economy.html':'IBM_Systems_Middleware_Integration',
'/middleware/integration/en-us/enterprise-service-bus-esb.html':'IBM_Systems_Middleware_Integration',
'/middleware/smarter-process/en-us/index.html':'IBM_Systems_Middleware_SmarterProcess',
'/software/commerce/cpq-information-kit.html':'IBM_Commerce_eCommerceCategory',
'/software/commerce/support':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/contact-center':'IBM_Commerce_eCommerceCategory',
'/software/procurement-solutions/podcasts/cpo_roundtable/index.html':'IBM_Commerce_Procurement',
'/software/procurement-solutions/podcasts/cpo_roundtable_two/index.html':'IBM_Commerce_Procurement',
'/software/collaboration/events/nwtw_tour/analytics.html':'IBM_Social_NWTW',
'/software/collaboration/events/nwtw_tour/cities.html':'IBM_Social_NWTW',
'/software/collaboration/events/nwtw_tour/finance.html':'IBM_Social_NWTW',
'/software/collaboration/events/nwtw_tour/marketing.html':'IBM_Social_NWTW',
'/software/collaboration/events/nwtw_tour/stayconnected.html':'IBM_Social_NWTW',
'/software/commerce/b2c-ecommerce':'IBM_Commerce_eCommerceCategory',
'/software/commerce/forrester-report/index.html':'IBM_Commerce_eCommerce',
'/software/commerce/offers/b2b-ecommerce-cloud.html':'IBM_Commerce_eCommerceCategory',
'/software/commerce/order-management/inventory':'IBM_Commerce_eCommerceCategory',
'/software/commerce/order-management/reverselogistics':'IBM_Commerce_eCommerceCategory',
'/software/commerce/order-management/scheduling':'IBM_Commerce_eCommerceCategory',
'/software/commerce/order-management/store':'IBM_Commerce_eCommerceCategory',
'/software/commerce/products/ilog-supply-chain-apps-div/index.html':'IBM_Commerce_eCommerce',
'/software/commerce/products/transportation-management_devs':'IBM_Commerce_eCommerceCategory',
'/software/commerce/services':'IBM_Commerce_eCommerceCategory',
'/software/commerce/websphere-commerce/technology-partners':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/customer-experience-management':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/extended':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/increase-customer-engagement':'IBM_Commerce_eCommerce',
'/software/genservers/commerce/mobile':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/mtools':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/omni-channel-retailing':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/order-fulfillment':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/sales-configuration':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/socialcommerce':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerce/storefronts':'IBM_Commerce_eCommerceCategory',
'/software/info/coremetrics-software':'IBM_Commerce_MarketingCategory',
'/software/info/demandtec':'IBM_Commerce_MarketingCategory',
'/software/info/emptoris':'IBM_Commerce_ProcurementCategory',
'/software/info/emptoris/download.html':'IBM_Commerce_ProcurementCategory',
'/software/info/silverpop':'IBM_Commerce_MarketingCategory',
'/software/info/sterling-commerce':'IBM_Commerce_eCommerceCategory',
'/software/info/tealeaf':'IBM_Commerce_MarketingCategory',
'/software/info/unica':'IBM_Commerce_MarketingCategory',
'/software/info/xtify':'IBM_Commerce_Marketing',
'/software/marketing-solutions/accountable-advertising-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/benchmark':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/benchmark/metrics.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/benchmark-hub':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/benchmark-reports':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/cafe':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/convert-digital-prospects-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/digital-analytics-partners/adtarget.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/digital-analytics-partners/ddx.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/digital-analytics-partners/index.html':'IBM_Commerce_Marketing',
'/software/marketing-solutions/digital-analytics-partners/livemail.html':'IBM_Commerce_Marketing',
'/software/marketing-solutions/digital-data-exchange/index.html':'IBM_Commerce_Marketing',
'/software/marketing-solutions/email-marketing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/event-based-marketing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/experienceone':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/experienceone/downloads.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/experienceone/solutions.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/interactive-marketing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/live-profiles':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/marketing-operations-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/mobile-marketing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/omni-channel-pricing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/personalization-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/promotion-management-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/real-time-marketing-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/retargeting-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/social-media-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/targeted-recommendations-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/web-analytics':'IBM_Commerce_MarketingCategory',
'/software/procurement-solutions/2014-IBM-CPO-Study':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/demos/emptoris-executive-series':'IBM_Commerce_Procurement',
'/software/procurement-solutions/emptoris/support':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/latest-release':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/offers/odw-cpo-survey-2013.html':'IBM_Commerce_Procurement',
'/software/procurement-solutions/offers/odw-optimizing-source-to-contract-process.html':'IBM_Commerce_Procurement',
'/software/procurement-solutions/offers/why-contract-management':'IBM_Commerce_Procurement',
'/software/procurement-solutions/offers/why-telecom-expense-management':'IBM_Commerce_Procurement',
'/software/procurement-solutions/services':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/virtual-supply-master':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/webinars/az-telecom-sourcing-expense':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/big-data-procurement-impact':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/emptoris-customer':'IBM_Commerce_ProcurementCategory',
'/software/procurement-solutions/webinars/emptoris-services':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/emptoris-spend-analysis':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/max-sav-mobile-telecom-expense':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/odw-cpo-survey-2014':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/procurement-solns':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/supplier-risk-management':'IBM_Commerce_Procurement',
'/software/procurement-solutions/webinars/telecom-spend-mgmt':'IBM_Commerce_Procurement',
'/software/products/en/ibm-advanced-deal-management':'IBM_Commerce_Marketing',
'/software/products/en/assortment-optimization-ibm':'IBM_Commerce_Marketing',
'/software/products/en/ibm-basket-insights':'IBM_Commerce_Marketing',
'/software/products/en/Call-Center':'IBM_Commerce_eCommerce',
'/software/products/en/campaign-management':'IBM_Commerce_MarketingCategory',
'/software/products/en/campaign-optimization':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/b2b-commerce':'IBM_Commerce_eCommerceCategory',
'/software/products/en/category/b2c-commerce':'IBM_Commerce_eCommerceCategory',
'/software/products/en/category/business-mail':'IBM_Social_SoftwareCatalog',
'/software/products/en/category/collaborative-applications':'IBM_Social_SoftwareCatalog',
'/software/products/en/category/customer-analytics':'IBM_Commerce_eCommerceCategory',
'/software/products/en/category/digital-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/enterprise-category-spend-management':'IBM_Commerce_ProcurementCategory',
'/software/products/en/category/enterprise-social-network':'IBM_Social_SoftwareCatalog',
'/software/products/en/category/marketing-merchandising':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/mobile-engagement':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/omni-channel-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/omni-channel-merchandising':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/procurement':'IBM_Commerce_ProcurementCategory',
'/software/products/en/category/real-time-personalization':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/source-to-contract':'IBM_Commerce_ProcurementCategory',
'/software/products/en/category/supplier-management-risk-compliance':'IBM_Commerce_ProcurementCategory',
'/software/products/en/connections-files':'IBM_Social_SoftwareCatalog',
'/software/products/en/contract-management':'IBM_Commerce_ProcurementCategory',
'/software/products/en/contract-management-buy-side':'IBM_Commerce_ProcurementCategory',
'/software/products/en/contract-management-sell-side':'IBM_Commerce_eCommerceCategory',
'/software/products/en/customer-behavior-analysis':'IBM_Commerce_MarketingCategory',
'/software/products/en/customer-experience-analytics-cloud':'IBM_Commerce_MarketingCategory',
'/software/products/en/customer-experience-integrations':'IBM_Commerce_MarketingCategory',
'/software/products/en/customer-lifecycle-conversion':'IBM_Commerce_MarketingCategory',
'/software/products/en/customer-marketing-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/customer-service-optimization':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-connect-data-analysis':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-connect-voice-customer':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-connect-web-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-impact':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-lifecycle':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-mobile':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-overstat':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-platform':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-reveal':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-verify':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-view':'IBM_Commerce_MarketingCategory',
'/software/products/en/ibmdeal-management':'IBM_Commerce_Marketing',
'/software/products/en/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/display-ad-targeting':'IBM_Commerce_MarketingCategory',
'/software/products/en/distributed-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/email-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/email-optimization':'IBM_Commerce_MarketingCategory',
'/software/products/en/enterprise-digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/enterprise-marketing-operations':'IBM_Commerce_MarketingCategory',
'/software/products/en/event-based-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/ibmconnectionsdocs':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-connections-docs-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/impression-attribution':'IBM_Commerce_MarketingCategory',
'/software/products/en/ibmmarkdown-optimization':'IBM_Commerce_Marketing',
'/software/products/en/mobile-push-notification':'IBM_Commerce_MarketingCategory',
'/software/products/en/mobile-web-push':'IBM_Commerce_Marketing',
'/software/products/en/multichannel-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/on-demand-marketing-operations':'IBM_Commerce_MarketingCategory',
'/software/products/en/opportunity-detect':'IBM_Commerce_MarketingCategory',
'/software/products/en/personalized-content-recommendations':'IBM_Commerce_MarketingCategory',
'/software/products/en/personalized-product-recommendations':'IBM_Commerce_MarketingCategory',
'/software/products/en/predictive-marketing-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/ibmprice-management':'IBM_Commerce_Marketing',
'/software/products/en/ibmprice-optimization':'IBM_Commerce_Marketing',
'/software/products/en/program-management':'IBM_Commerce_ProcurementCategory',
'/software/products/en/ibm-promotion-execution':'IBM_Commerce_Marketing',
'/software/products/en/ibm-promotion-optimization':'IBM_Commerce_Marketing',
'/software/products/en/ibm-promotion-planning':'IBM_Commerce_Marketing',
'/software/products/en/real-time-inbound-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/services-procurement':'IBM_Commerce_ProcurementCategory',
'/software/products/en/shopper-insights-ibm':'IBM_Commerce_Marketing',
'/software/products/en/silverpop-engage':'IBM_Commerce_MarketingCategory',
'/software/products/en/social-media-digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/spend-analysis':'IBM_Commerce_ProcurementCategory',
'/software/products/en/supplier-lifecycle-management':'IBM_Commerce_ProcurementCategory',
'/software/products/en/targeted-email':'IBM_Commerce_MarketingCategory',
'/software/products/en/warehouse-management':'IBM_Commerce_eCommerceCategory',
'/software/products/en/websphere-commerce-developer':'IBM_Commerce_eCommerceCategory',
'/software/products/en/commerce-on-cloud':'IBM_Commerce_eCommerce',
'/software/products/en/configure-price-quote':'IBM_Commerce_eCommerce',
'/software/products/en/order-management':'IBM_Commerce_eCommerce',
'/software/products/en/websphere-commerce':'IBM_Commerce_eCommerce',
'/software/products/en/websphere-commerce-enterprise':'IBM_Commerce_eCommerce',
'/software/products/en/websphere-commerce-express':'IBM_Commerce_eCommerce',
'/software/products/en/websphere-commerce-professional':'IBM_Commerce_eCommerce',
'/software/commerce/flexible-buying/b2c.html':'IBM_Commerce_eCommerce',
'/resources/experience/connections':'IBM_Social_TopLevel',
'/resources/experience/notes':'IBM_Social_TopLevel',
'/cloud-computing/social/br/pt':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/cn/zh':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/de/de':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/es/es':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/fr/fr':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/it/it':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/jp/ja':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/tw/zh':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en/emailcalendar':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en/maintenance':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en/planspricing':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en/startatrial':'IBM_Social_ConnectionsMarketing',
'/cloud-computing/social/us/en/support':'IBM_Social_ConnectionsMarketing',
'/commerce/us-en':'IBM_Commerce_TopLevel',
'/commerce/us-en':'IBM_Commerce_Marketing',
'/commerce/us-en':'IBM_Commerce_TopLevel',
'/developerworks/cn/lotus/domino-daos':'IBM_Social_Developerworks',
'/developerworks/jp/lotus':'IBM_Social_Developerworks',
'/developerworks/jp/lotus/products/lnd':'IBM_Social_Developerworks',
'/developerworks/lotus':'IBM_Social_Developerworks',
'/developerworks/lotus/blogs.html':'IBM_Social_Developerworks',
'/developerworks/lotus/digital_experience.html':'IBM_Social_Developerworks',
'/developerworks/lotus/downloads.html':'IBM_Social_Developerworks',
'/developerworks/lotus/products/notesdomino/notessql':'IBM_Social_Developerworks',
'/developerworks/lotus/socialcollab.html':'IBM_Social_Developerworks',
'/developerworks/lotus/toolkits.html':'IBM_Social_Developerworks',
'/developerworks/mobile':'IBM_Commerce_TopLevel',
'/developerworks/rational/library/integrate-blueworks-live-business-process':'IBM_Systems_Middleware_SmarterProcess',
'/ibm/puresystems/cn/zh':'IBM_Systems_Middleware',
'/ibm/puresystems/jp/ja':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en/hybrid-cloud':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en/pf_pureapplication.html':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en/pf_pureflex.html':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en/puredata-system-for-analytics.html':'IBM_Systems_Middleware',
'/madewithibm/de/de/social':'IBM_Social_MadeWithIBM',
'/madewithibm/ru/ru/social':'IBM_Social_MadeWithIBM',
'/madewithibm/social':'IBM_Social_MadeWithIBM',
'/madewithibm/uk/en/social':'IBM_Social_MadeWithIBM',
'/marketplace/cloud/digital-marketing-and-lead-management/us/en-us':'IBM_Commerce_Marketing',
'/marketplace/cloud/mobile-device-management/us/en-us':'IBM_Commerce_TopLevel',
'/marketplace/cloud/mobile-web-push-notifications/us/en-us':'IBM_Commerce_Marketing',
'/marketplace/cloud/Social-business-collaboration/us/en-us':'IBM_Social_CloudMarketplace',
'/mobilefirst/ar/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/be/en':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/br/pt':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/br/pt/downloads':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/cl/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/cn/zh':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/co/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/de/de':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/ec/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/in/en':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/jp/ja':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/jp/ja/mobilefirst-for-ios':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/mx/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/pe/es':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/uk/en':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/bring-your-own-device/byod.html':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/downloads':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/good-apps-bad-apps.html':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/mobile-case-studies':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/mobile-platform.html':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/mobilefirst-for-ios':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobilefirst-for-ios':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobilefirst-for-ios/industries/overview':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobilefirst-for-ios/mobilefirst-for-ios-solutions.html':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobilefirst-for-ios/services/ios-solutions':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobilefirst-for-ios/services/overview':'IBM_GBS_Services_MobileFirst_Apple',
'/mobilefirst/us/en/mobile-platform.html':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/mobile-platform.html':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/mobile-solutions':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/news':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/us/en/portraits-of-mobility':'IBM_GBS_Services_MobileFirst',
'/mobilefirst/ve/es':'IBM_GBS_Services_MobileFirst',
'/services/en-us':'IBM_GBS_Services_Industries',
'/smarterplanet/us/en/healthcare_solutions/ideas':'IBM_GBS_Services_Healthcare_Other',
'/commerce/us-en':'IBM_Commerce_TopLevel',
'/smarterplanet/us/en/think/healthcare':'IBM_GBS_Services_Healthcare_Other',
'/social-business/br/pt/geo':'IBM_Social_VerseMarketing',
'/social-business/cn/zh/newway':'IBM_Social_VerseMarketing',
'/social-business/cn/zh/newway/persona.html':'IBM_Social_VerseMarketing',
'/social-business/de/de/newway':'IBM_Social_VerseMarketing',
'/social-business/fr/fr/newway':'IBM_Social_VerseMarketing',
'/social-business/it/it/newway':'IBM_Social_VerseMarketing',
'/social-business/jp/ja/newway':'IBM_Social_VerseMarketing',
'/social-business/jp/ja/newway/sign-up':'IBM_Social_VerseMarketing',
'/social-business/us/en':'IBM_Social_SocialBusiness',
'/social-business/us/en/newway':'IBM_Social_VerseMarketing',
'/social-business/us/en/newway/persona.html':'IBM_Social_VerseMarketing',
'/marketing/iwm/dre/signup?source=swg-mnbt2&lang=en_US&disableCookie=Yes':'IBM_Social_VerseMarketing',
'/social-business/us/en/newway/sign-up/thankyou.html':'IBM_Social_VerseMarketing',
'/social-business/us/en/solutions.html':'IBM_Social_SocialBusiness',
'/social-business/us/en/startup':'IBM_Social_SocialBusiness',
'/software/rational':'IBM_Systems_Middleware_Other',
'/software/tivoli':'IBM_Systems_Middleware_Other',
'/software/websphere':'IBM_Systems_Middleware_Integration',
'/web/portal/commerce/Home/Welcome/!ut/p/z1/04_Sj9CPykssy0xPLMnMz0vMAfIjo8zind0dPUzMfQwM_AMc3Qw8_Uz8LBxN_IwNXA30vfSjgAoMcABHA5AJRkW-zr7p-lEFiSUZupl5afn6EcWpRWWZyam6KanF2frh-lFgU-DWWJgbgq0xD7QMDjAy8DSDKsBjTUF2VJJZkKMiADLrfQM!/dz/d5/L2dBISEvZ0FBIS9nQSEh':'IBM_Commerce_eCommerce',
'/web/portal/software/websphere':'IBM_Commerce_eCommerceCategory',
'/web/portal/software/websphere/http__web_portal_software_websphere__wca_blank.html':'IBM_Commerce_TopLevel',
'/software/analytics/spss/support/get_spss.html':'IBM_Systems_TopLevel',
'/software/be/lotus':'IBM_Social_CollaborationSolutions',
'/software/be/rational':'IBM_Systems_Global_SWOther',
'/software/br/lotus/products/notes':'IBM_Social_CollaborationSolutions',
'/software/br/websphere':'IBM_Systems_Middleware_Integration',
'/software/cn/events/insight':'IBM_Events_Insight',
'/software/cn/lotus':'IBM_Social_CollaborationSolutions',
'/software/cn/smartercommerce':'IBM_Commerce_TopLevel',
'/software/cn/websphere':'IBM_Systems_Middleware_Integration',
'/systems/cn/z/software/index.html':'IBM_Systems_Hardware_zSystems',
'/software/collaboration/digitalexperience':'IBM_Systems_Middleware_DigitalExperience',
'/software/collaboration/events/connect':'IBM_Social_Connect',
'/software/collaboration/events/connect':'IBM_Social_Connect',
'/software/collaboration/events/connected/agenda.html':'IBM_Social_Connect',
'/software/collaboration/events/connected/registrationoverview.html':'IBM_Social_Connect',
'/software/collaboration/events/connected/social.html':'IBM_Social_Connect',
'/software/collaboration/events/nwtw_tour':'IBM_Social_NWTW',
'/software/commerce/b2b/b2b-collaboration-network':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/b2b-services-analytics.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/cloud-services':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/cloud-services/downloads.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/payments':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/b2b-integration/gateways':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/library':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/b2b-integration/meig-videos.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/mrm-videos.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/process-applications':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/b2b-integration/spe-videos.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/cloud-services/integration-services':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/edi':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/products/inefficient-partner-onboarding':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/products/inefficient-partner-onboarding/downloads.html':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/products/integration-services-basic/features.html':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/products/lack-of-visibility':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/products/limited-resources':'IBM_Commerce_B2BIntegration',
'/software/commerce/b2b/products/manual-paper-based-processes':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/b2b/products/manual-paper-based-processes/downloads.html':'IBM_Commerce_B2BIntegration',
'/software/commerce/managed-file-transfer/products/data-movement':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/products/data-movement-compliance-requirements':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/products/data-movement-visibility':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/products/file-transfer-security':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/products/ftp-transfers':'IBM_Commerce_B2BIntegration',
'/software/commerce/managed-file-transfer/products/large-transfers':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/products/onboarding-timeframes':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/managed-file-transfer/solutions/grc4data':'IBM_Commerce_B2BIntegrationCategory',
'/software/commerce/offers/b2b-ecommerce-cloud.html':'IBM_Social_Kenexa',
'/software/commerce/offers/de/b2b-ecommerce-cloud.html':'IBM_Commerce_B2BIntegration',
'/software/commerce/optimization/cplex-optimizer':'IBM_Commerce_TopLevel',
'/software/commerce/optimization/linear-programming':'IBM_Commerce_TopLevel',
'/software/commerce/optimization/modeling':'IBM_Commerce_TopLevel',
'/software/commerce/websphere-commerce/technology-partners':'IBM_Commerce_eCommerceCategory',
'/software/data/ims':'IBM_Systems_Hardware_zSystems',
'/software/de/lotus':'IBM_Social_CollaborationSolutions',
'/software/de/websphere':'IBM_Systems_Middleware_Integration',
'/software/ecm/events/content2015':'IBM_Events_TopLevel',
'/software/es/lotus':'IBM_Social_CollaborationSolutions',
'/software/events/amplify':'IBM_Events_Amplify',
'/software/events/amplify/agenda':'IBM_Events_Amplify_Agenda',
'/software/events/amplify/agenda/schedule':'IBM_Events_Amplify_Agenda',
'/software/events/amplify/keynotes':'IBM_Events_Amplify_Speakers',
'/software/events/amplify/partners':'IBM_Events_Amplify_Sponsors',
'/software/events/amplify/register':'IBM_Events_Amplify_Registration',
'/software/events/amplify/sponsor':'IBM_Events_Amplify_Sponsors',
'/software/events/amplify/index.html':'IBM_Events_Amplify',
'/software/events/insight':'IBM_Events_Insight_Agenda',
'/software/events/insight/agenda/schedule':'IBM_Events_Insight_Agenda',
'/software/events/insight/agenda/schedule/curriculum':'IBM_Events_Insight_Agenda',
'/software/events/insight/agenda/schedule/curriculum':'IBM_Events_Insight_Agenda',
'/software/events/insight/expo/info':'IBM_Events_Insight_Sponsors',
'/software/events/insight/hotel':'IBM_Events_Insight_Registration',
'/software/events/insight/keynotes':'IBM_Events_Insight_Speakers',
'/software/events/insight/partners':'IBM_Events_Insight_Sponsors',
'/software/events/insight/registration':'IBM_Events_Insight_Registration',
'/software/fr/lotus':'IBM_Social_CollaborationSolutions',
'/software/genservers/commerce/storefronts':'IBM_Commerce_eCommerceCategory',
'/software/genservers/commerceproductline/e-commerce_software':'IBM_Commerce_eCommerceCategory',
'/software/htp/cics':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/in/lotus':'IBM_Social_CollaborationSolutions',
'/software/in/websphere':'IBM_Systems_Middleware_Integration',
'/software/info/bpm/what-is-bpm':'IBM_Systems_Middleware_SmarterProcess',
'/software/info/coremetrics-software':'IBM_Commerce_MarketingCategory',
'/software/info/curam':'IBM_GBS_Other',
'/software/info/demandtec':'IBM_Commerce_MarketingCategory',
'/software/info/emptoris':'IBM_Commerce_ProcurementCategory',
'/software/info/silverpop':'IBM_Commerce_MarketingCategory',
'/software/info/sterling-commerce':'IBM_Commerce_B2BIntegrationCategory',
'/software/info/sterling-commerce/downloads.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/info/tealeaf':'IBM_Commerce_MarketingCategory',
'/software/info/unica':'IBM_Commerce_MarketingCategory',
'/software/integration/business-rule-management/jrules-family':'IBM_Systems_Middleware_SmarterProcess',
'/software/integration/cast-iron-cloud-integration/salesforce-integration':'IBM_Systems_Middleware_Integration',
'/software/integration/lombardi-edition':'IBM_Systems_Middleware',
'/software/integration/wbimessagebroker/requirements':'IBM_Systems_Middleware',
'/software/integration/wdatastagetx/adapters.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wdatastagetx/editions.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/entpacks/sapr3.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wdatastagetx/extpacks.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/library':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/library/v81.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/library/v82.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/library/v83.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/library/v84.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/news':'IBM_Commerce_B2BIntegration',
'/software/integration/wdatastagetx/news/sepa.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wdatastagetx/packs.html':'IBM_Commerce_B2BIntegrationCategory',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/support/docview.wss?uid=swg27045341':'IBM_Commerce_B2BIntegration',
'/software/integration/wdatastagetx/tradingmgr.html':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wdatastagetx/wdatastagetx-library':'IBM_Commerce_B2BIntegration',
'/software/integration/wmq/clients':'IBM_Systems_Middleware_Integration',
'/software/integration/wmq/explorer':'IBM_Systems_Middleware_Integration',
'/software/integration/wmq/explorer/downloads':'IBM_Systems_Middleware_Integration',
'/software/integration/wps':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/integration/wspartnergateway/about':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/advanced':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/enterprise':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/express':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/express/library/infocenter/download.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/library':'IBM_Commerce_B2BIntegrationCategory',
'/software/integration/wspartnergateway/library/expressv600.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/library/infocenter/download.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/library/v610.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/library/v611.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/standards.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/support':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/whatsnew/details.html':'IBM_Commerce_B2BIntegration',
'/software/integration/wspartnergateway/wspartnergateway-pricing.html':'IBM_Commerce_B2BIntegration',
'/software/jp/cmp/cds.html':'IBM_Systems_Middleware_Integration',
'/software/jp/cmp/was':'IBM_Systems_Middleware_Integration',
'/software/jp/events/spss_pro':'IBM_Events_TopLevel',
'/software/jp/lotus':'IBM_Social_CollaborationSolutions',
'/software/jp/websphere':'IBM_Systems_Middleware_Integration',
'/software/lotus':'IBM_Social_CollaborationSolutions',
'/software/lotus/category/appdev':'IBM_Social_CollaborationSolutions',
'/software/lotus/category/messaging':'IBM_Social_CollaborationSolutions',
'/software/lotus/index.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/additionalswentitlements.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/ceobundles.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/clientlicensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/domino-express-licensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/domino-pvu-licensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/domino-server-licensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/licensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/licensingfaq.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/notesanddomino/userlicensing.html':'IBM_Social_CollaborationSolutions',
'/software/lotus/products':'IBM_Social_CollaborationSolutions',
'/software/marketing-solutions/benchmark-hub':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/benchmark-hub/alert.html':'IBM_Commerce_MarketingCategory',
'/software/info/coremetrics-software':'IBM_Commerce_Marketing',
'/software/marketing-solutions/experienceone':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/experienceone/solutions.html':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/ibmandfacebook':'IBM_Commerce_TopLevel',
'/software/marketing-solutions/marketing-operations-solution':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/roles/digital-marketing-and-analytics':'IBM_Commerce_MarketingCategory',
'/software/marketing-solutions/web-analytics':'IBM_Commerce_MarketingCategory',
'/software/nl/connect':'IBM_Events_Connect',
'/software/nl/connect/agenda-keynote.html':'IBM_Events_Connect_Agenda',
'/software/os/systemz/2015summit':'IBM_Systems_Events',
'/software/os/systemz/2015summit/register.html':'IBM_Systems_Events',
'/software/os/systemz/en_US':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/events/calendar':'IBM_Systems_Events',
'/software/pl/lotus':'IBM_Social_CollaborationSolutions',
'/software/pl/solutionsconnect':'IBM_Events_TopLevel',
'/cloud-computing/us/en/interconnect':'IBM_Systems_Events',
'/software/ru/lotus':'IBM_Social_CollaborationSolutions',
'/software/ru/websphere':'IBM_Systems_Middleware_Integration',
'/software/smarterworkforce':'IBM_Social_SmarterWorkforce',
'/software/smarterworkforce/kenexa-unlocked':'IBM_Social_SmarterWorkforce',
'/software/solutions/finance/merva':'IBM_Commerce_B2BIntegrationCategory',
'/software/solutions/finance/merva/features':'IBM_Commerce_B2BIntegrationCategory',
'/software/solutions/finance/merva/library':'IBM_Commerce_B2BIntegration',
'/software/solutions/finance/merva/sysreqs':'IBM_Commerce_B2BIntegration',
'/software/solutions/soa':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/downloads.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/gov':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/innov8/index.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/innov8/cityone':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/innov8/full.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/innov8/innov8game.jsp':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/what-is-soa.html':'IBM_Systems_Middleware_Integration',
'/software/tivoli/csi/backup-recovery':'IBM_Systems_Middleware',
'/software/tivoli/csi/cloud-storage':'IBM_Systems_Hardware_Storage',
'/software/uk/swfc-2015':'IBM_Events_TopLevel',
'/software/uk/websphere':'IBM_Systems_Middleware_Integration',
'/software/webservers/appserv/was/performance.html':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/webservers/appserv/was/requirements':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/webservers/appserv/wasfamily/compare.html':'IBM_Systems_Middleware_ApplicationPlatform',
'/cloud-computing/us/en/interconnect':'IBM_Events_Interconnect',
'/software/websphere/products/business-rule-management':'IBM_Systems_Middleware_SmarterProcess',
'/software/websphere/products/business-rule-management/whatis':'IBM_Systems_Middleware_SmarterProcess',
'/services/continuity/resilience.nsf/pages/atlanta':'IBM_GTS_Resiliency',
'/services/continuity/resilience.nsf/pages/boulder':'IBM_GTS_Resiliency',
'/services/continuity/resilience.nsf/pages/san-jose':'IBM_GTS_Resiliency',
'/software/products/de/conn':'IBM_Social_SoftwareCatalog',
'/software/products/de/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/de/ibmdomino':'IBM_Social_SoftwareCatalog',
'/software/products/de/ibm-kenexa-brassring-on-cloud':'IBM_Social_Kenexa',
'/software/products/de/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/de/ibmsame':'IBM_Social_SoftwareCatalog',
'/software/products/de/notesanddominofamily':'IBM_Social_SoftwareCatalog',
'/software/products/de/notetrav':'IBM_Social_SoftwareCatalog',
'/software/products/de/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/en/api-management':'IBM_Systems_Middleware_Integration_WebSphereAPIManagement',
'/software/products/en/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/appserv-wasexp':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/appserv-wasfordev':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/appserv-was-liberty-core':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/appserv-wasnd':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/b2b-integrator':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/business-monitor':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/business-process-manager-advanced':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/business-process-manager-cloud':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/business-process-manager-standard':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/campaign-management':'IBM_Commerce_MarketingCategory',
'/software/products/en/castiron-cloud-integration':'IBM_Systems_Middleware_Integration',
'/software/products/en/category/application-infrastructure':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/category/application-performance-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/category/talent-acquisition':'IBM_Social_SmarterWorkforce',
'/software/products/en/category/b2b-commerce':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/category/b2b-integration':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/category/commerce':'IBM_Commerce_TopLevel',
'/software/products/en/category/connectivity-integration-soa':'IBM_Systems_Middleware_Integration',
'/software/products/en/category/customer-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/digital-experience':'IBM_Systems_Middleware_DigitalExperience',
'/software/products/en/category/digital-marketing':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/enterprisemodernization':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/category/enterprise-social-mail':'IBM_Social_SoftwareCatalog',
'/software/products/en/category/it-service-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/category/managed-file-transfer':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/category/marketing-merchandising':'IBM_Commerce_MarketingCategory',
'/software/products/en/category/omni-channel-marketing':'IBM_Commerce_eCommerceCategory',
'/software/products/en/category/operational-decision-management':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/category/procurement':'IBM_Commerce_ProcurementCategory',
'/software/products/en/category/storage-software':'IBM_Systems_Hardware_Storage',
'/software/products/en/category/enterprise-social-mail':'IBM_Social_SoftwareCatalog',
'/software/products/en/category/talent-acquisition':'IBM_Social_SmarterWorkforce',
'/software/products/en/category/talent-management':'IBM_Social_SmarterWorkforce',
'/software/products/en/category/talent-management':'IBM_Social_SmarterWorkforce',
'/software/products/en/category/unified-communications-collaboration':'IBM_Social_SoftwareCatalog',
'/software/products/en/ccompfami':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/cics-ctg':'IBM_Systems_Middleware_ApplicationPlatform_CICSServers',
'/software/products/en/cics-explorer':'IBM_Systems_Middleware_ApplicationPlatform_WebSphereTraditionalTools',
'/software/products/en/cics-tservers':'IBM_Systems_Middleware_ApplicationPlatform_CICSServers',
'/software/info/app/ecatalog/index.html':'IBM_Systems_Hardware_zSystems',
'/software/products/en/cobocompfami':'IBM_Systems_Middleware_ApplicationPlatform_Compilers',
'/software/products/en/commerce-on-cloud':'IBM_Commerce_eCommerceCategory',
'/software/products/en/community-development-services':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/configuration-manager':'IBM_Commerce_B2BIntegration',
'/software/products/en/configure-price-quote':'IBM_Commerce_eCommerceCategory',
'/software/products/en/conn':'IBM_Social_SoftwareCatalog',
'/software/products/en/connect-direct':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/connections-cloud-s2':'IBM_Social_SoftwareCatalog',
'/software/products/en/connections-content-mgr':'IBM_Social_SoftwareCatalog',
'/software/products/en/connections-family':'IBM_Social_SoftwareCatalog',
'/software/products/en/connections-files-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/connectorsap':'IBM_Social_SoftwareCatalog',
'/software/products/en/contract-management':'IBM_Commerce_ProcurementCategory',
'/software/products/en/control-center':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/control-desk':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/cx-mobile':'IBM_Commerce_MarketingCategory',
'/software/products/en/cx-platform':'IBM_Commerce_MarketingCategory',
'/software/products/en/datapower-gateway':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/info/app/ecatalog/index.html':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/products/en/data-sync-manager':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/en/ibmconnectionsdocs':'IBM_Social_SoftwareCatalog',
'/software/products/en/e-invoicing':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/email-optimization':'IBM_Commerce_MarketingCategory',
'/software/products/en/entecoboforzos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/expeditor':'IBM_Social_SoftwareCatalog',
'/software/products/en/fax-conversion':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/file-gateway':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/file-transfer-service':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/financial-transaction-manager':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/gentran':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/high-speed-file-transfer':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/http-servers':'IBM_Systems_Hardware_zSystems',
'/software/products/en/ibmbluelive':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/ibmconncontedit':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-connections-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-connections-cloud-s1':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-connections-meetings-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-connections-social-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmconnsuit':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmcustxpersuit':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/ibm-datapower-gateways':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/products/en/ibmdomino':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmdominodesigner':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmdominoexpress':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-domino-hypervisor-edition':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmenterpriseintegrator':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmformexpebuil':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/ibmformfami':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/ibm-integration-bus':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/en/ibm-kenexa-behavioral-assessments-for-executive-roles-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-behavioral-assessments-for-hourly-roles-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-behavioral-assessments-for-professional-and-managerial-roles-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-brassring-candidate-relationship-manager':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-brassring-on-cloud':'IBM_Social_SmarterWorkforce',
'/software/info/app/ecatalog/index.html':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-companalyst-enterprise-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-companalyst-executive-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-companalyst-job-description-builder-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-companalyst-market-data-for-us-on-cloud':'IBM_Social_Kenexa',
'/software/info/app/ecatalog/index.html':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-interview-builder-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-ipas-on-cloud':'IBM_Social_Kenexa',
'/software/info/app/ecatalog/index.html':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-lcms-premier-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-lms-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-organizational-cultural-insight-survey':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-prove-it-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-rated-behavioral-assessments-for-managerial-and-leadership-roles-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-skills-assessments-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-skills-manager-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-survey-advantage-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-survey-enterprise':'IBM_Social_SmarterWorkforce',
'/software/products/en/ibm-kenexa-survey-enterprise-360-on-cloud':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-talent-frameworks':'IBM_Social_Kenexa',
'/software/products/en/ibm-kenexa-talent-manager-on-cloud':'IBM_Social_Kenexa',
'/software/info/app/ecatalog/index.html':'IBM_Social_Kenexa',
'/software/products/en/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/en/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmsame':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmsameuniftele':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmsameunifteleliteclie':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmsmarnote':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-social-learning':'IBM_Social_Kenexa',
'/software/products/en/ibmtivolinetcoolomnibus':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/ibm-verse':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmwebcontmana':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/ibm-web-mail-cloud':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibmxworserv':'IBM_Social_SoftwareCatalog',
'/software/products/en/information-broker':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/inotes':'IBM_Social_SoftwareCatalog',
'/software/products/en/integration-bus-advanced':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/en/integration-designer':'IBM_Systems_Middleware_Integration_WebSphereMQMessaging',
'/software/products/en/integration-services-basic':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/integration-services-plus':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/licensemetrictool':'IBM_Systems_Middleware',
'/software/products/en/lotuquicfami':'IBM_Social_SoftwareCatalog',
'/software/products/en/lotusymp':'IBM_Social_SoftwareCatalog',
'/software/products/en/managed-as2-gateway':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/messagesight':'IBM_Social_SoftwareCatalog',
'/software/products/en/mobile-connect':'IBM_Social_SoftwareCatalog',
'/software/products/en/mobilefirstplatform':'IBM_Commerce_TopLevel',
'/software/products/en/mobile-push-notification':'IBM_Social_SoftwareCatalog',
'/software/products/en/mq-appliance-m2000':'IBM_Systems_Middleware_Integration',
'/software/products/en/netcool-network-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/netcool-operations-insight':'IBM_Systems_Middleware',
'/software/products/en/notesanddominofamily':'IBM_Social_SoftwareCatalog',
'/software/products/en/notetrav':'IBM_Social_SoftwareCatalog',
'/software/products/en/odm':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/order-management':'IBM_Commerce_TopLevel',
'/software/products/en/pcomm':'IBM_Systems_Middleware_Integration',
'/software/products/en/portalserver':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/predictive-hiring-hourly-roles':'IBM_Social_Kenexa',
'/software/products/en/predictive-retention':'IBM_Social_Kenexa',
'/software/products/en/protector':'IBM_Social_SoftwareCatalog',
'/software/products/en/quickfile':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/ratideveandtestenviforsystz':'IBM_Systems_Hardware_zSystems',
'/software/products/en/rationalhostondemand':'IBM_Systems_Middleware_ApplicationPlatform_TraditionalALM',
'/software/products/en/real-time-inbound-marketing':'IBM_Commerce_TopLevel',
'/software/products/en/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/en/secure-proxy':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/silverpop-engage':'IBM_Commerce_MarketingCategory',
'/software/products/en/social-programs':'IBM_GBS_TopLevel',
'/software/products/en/sourcing':'IBM_Commerce_TopLevel',
'/software/products/en/standards-processing-engine':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/storage-manager-virtual-environments':'IBM_Systems_Hardware_Storage',
'/software/products/en/talent-acquisition':'IBM_Social_Kenexa',
'/software/products/en/talent-insights':'IBM_Social_Kenexa',
'/software/products/en/tcamforappldiag':'IBM_Systems_Middleware_ITSvcManagement',
'/software/procurement-solutions/emptoris-rivermine-tem-div/index.html':'IBM_Commerce_Procurement',
'/software/products/en/tivoliapplicationdependencydiscoverymanager':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/tivoli-monitoring-composite-app-mgmt':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/spectrum-protect-snapshot':'IBM_Systems_Hardware_Storage',
'/software/products/en/spectrum-protect-family':'IBM_Systems_Hardware_Storage',
'/software/products/en':'IBM_Systems_Middleware',
'/software/products/en/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivostormanaexteedit':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivostormanaforsystbackandreco':'IBM_Systems_Hardware_Storage',
'/software/info/app/ecatalog/index.html':'IBM_Systems_Hardware_Storage',
'/software/products/en/workload-scheduler':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/spectrum-protect-suite':'IBM_Systems_Hardware_Storage',
'/software/products/en/vantforibmconn':'IBM_Social_SoftwareCatalog',
'/software/products/en/vantforibmsame':'IBM_Social_SoftwareCatalog',
'/software/products/en/ibm-virtual-storage-center':'IBM_Systems_Hardware_Storage',
'/software/products/en/was-overview':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/wbifn':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/wdatastagetx':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/wdi':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/web-forms':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/websmultbanktrantool':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/websphere-commerce':'IBM_Commerce_eCommerceCategory',
'/software/products/en/websphere-commerce-enterprise':'IBM_Commerce_eCommerceCategory',
'/software/products/en/websphere-extreme-scale':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/websphere-mq':'IBM_Systems_Middleware_Integration',
'/software/products/en/websphere-portal-family':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/wmq-fte':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en/workflow':'IBM_Social_SoftwareCatalog',
'/software/products/en/workforce-science':'IBM_Commerce_TopLevel',
'/software/products/en/wsesb':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/wspartnergateway':'IBM_Commerce_B2BIntegrationCategory',
'/software/products/en':'IBM_Commerce_B2BIntegration',
'/software/products/en/wsrr':'IBM_Systems_Middleware_Integration_WebSphereSOA',
'/software/products/en/xlcaix':'IBM_Systems_Middleware_ApplicationPlatform_Compilers',
'/software/products/en/xlcpp-aix':'IBM_Systems_Middleware_ApplicationPlatform_Compilers',
'/software/products/en/ztransaction-processing-facility':'IBM_Systems_Hardware_zSystems',
'/software/products/es/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/es/commerce-on-cloud':'IBM_Commerce_eCommerceCategory',
'/software/products/es/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/es/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/es/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/es/lotusymp':'IBM_Social_SoftwareCatalog',
'/software/products/es/notetrav':'IBM_Social_SoftwareCatalog',
'/software/products/es/silverpop-engage':'IBM_Commerce_MarketingCategory',
'/software/products/es':'IBM_Systems_Hardware_Storage',
'/software/products/fr/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/fr/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/fr/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/fr/notetrav':'IBM_Social_SoftwareCatalog',
'/software/products/it/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/ja/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/ja/digital-analytics':'IBM_Commerce_MarketingCategory',
'/software/products/ja/ibmbluelive':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/ja/ibmdomino':'IBM_Social_SoftwareCatalog',
'/software/products/ja/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/ja/notesanddominofamily':'IBM_Social_SoftwareCatalog',
'/software/products/ja/silverpop-engage':'IBM_Commerce_MarketingCategory',
'/software/products/ja/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/ja/websphere-mq':'IBM_Systems_Middleware_Integration',
'/software/products/nl/business-process-manager-cloud':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/ru/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/zh/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/zh/appserv-was-liberty-core':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/zh/category/application-infrastructure':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/zh/category/bpm-software':'IBM_Systems_Middleware_SmarterProcess',
'/errorpages/404.html':'IBM_Systems_Middleware',
'/software/products/zh/category/connectivity-integration-soa':'IBM_Systems_Middleware_Integration',
'/software/products/zh/category/it-service-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/zh/category/marketing-merchandising':'IBM_Commerce_MarketingCategory',
'/software/products/zh/category/procurement':'IBM_Commerce_TopLevel',
'/software/products/zh/category/cities-management':'IBM_Commerce_TopLevel',
'/software/products/zh/category/enterprise-social-mail':'IBM_Social_SoftwareCatalog',
'/software/products/zh/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/zh/ibmnotes':'IBM_Social_SoftwareCatalog',
'/software/products/zh/mobilefirstfoundation':'IBM_Commerce_TopLevel',
'/systems/ae/storage/flash/why-flash-storage-system.html':'IBM_Systems_Hardware_Storage',
'/systems/br/power/advantages/watson':'IBM_Systems_Hardware_Power',
'/systems/br/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/br/z':'IBM_Systems_Hardware_zSystems',
'/systems/infrastructure/cn/zh':'IBM_Systems_Hardware',
'/systems/cn/power':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/cn/power/migratetoibm':'IBM_Systems_Hardware_Power',
'/systems/cn/power/software/linux/powerlinux/powerlinux.shtml':'IBM_Systems_Hardware_Power',
'/systems/cn/power/solutions/watson':'IBM_Systems_Hardware_Power',
'/systems/cn/promotion/esg/stgcare/directentry.shtml':'IBM_Systems_Hardware',
'/systems/cn/storage':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/ds8000':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3500':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v5000/overview.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v7000/overview.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/network':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/san':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/cn/z':'IBM_Systems_Hardware_zSystems',
'/systems/data/flash/ae/migratetoibm':'IBM_Systems_Hardware',
'/systems/data/flash/see/spectrum/scale':'IBM_Systems_Hardware',
'/systems/data/flash/za/migratetoibm':'IBM_Systems_Hardware',
'/systems/data/flash/zsystems/themovie':'IBM_Systems_Hardware',
'/systems/de/power':'IBM_Systems_Hardware_Power',
'/systems/de/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/de/z':'IBM_Systems_Hardware_zSystems',
'/systems/director':'IBM_Systems_Hardware',
'/systems/director/downloads':'IBM_Systems_Hardware',
'/systems/director/downloads/agents.html':'IBM_Systems_Hardware',
'/systems/director/downloads/mgmtservers.html':'IBM_Systems_Hardware',
'/systems/director/downloads/plugins.html':'IBM_Systems_Hardware',
'/systems/director/editions':'IBM_Systems_Hardware',
'/systems/director/products.html':'IBM_Systems_Hardware',
'/systems/edge':'IBM_Systems_Hardware',
'/systems/edge/agenda.html':'IBM_Systems_Hardware',
'/systems/edge/connect.html':'IBM_Systems_Hardware',
'/systems/edge/executive_edge.html':'IBM_Systems_Hardware',
'/systems/edge/mediacenter.html':'IBM_Systems_Hardware',
'/systems/edge/index.html':'IBM_Systems_Hardware',
'/systems/edge/special_events.html':'IBM_Systems_Hardware',
'/systems/edge/sponsor.html':'IBM_Systems_Hardware',
'/systems/edge/technical_edge.html':'IBM_Systems_Hardware',
'/systems/es/edge':'IBM_Systems_Hardware',
'/systems/flex/storage/v7000':'IBM_Systems_Hardware',
'/systems/fr/power':'IBM_Systems_Hardware_Power',
'/systems/fr/z':'IBM_Systems_Hardware_zSystems',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/hk/storage/software-defined-storage':'IBM_Systems_Hardware_Storage',
'/systems/in/power':'IBM_Systems_Hardware_Power',
'/systems/in/shopbuyonline/maintenance_services.html':'IBM_Systems_Hardware',
'/systems/in/shopbuyonline/shop_online_products_systems_servers_storage_memory_processor_software_upgrades.html':'IBM_Systems_Hardware',
'/systems/in/storage':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/san':'IBM_Systems_Hardware_Storage',
'/systems/in/xoffers/rack.html':'IBM_Systems_Hardware',
'/systems/in/xoffers/tower.html':'IBM_Systems_Hardware',
'/systems/in/z':'IBM_Systems_Hardware_zSystems',
'/systems/in/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/info/used/computers.html':'IBM_Systems_Hardware',
'/systems/infrastructure/br/pt':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/big-data-analytics':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/cloud-servers':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/cloud-servers/cloud-technology.html':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/it-infrastructure-matters/infrastructure-facts.html':'IBM_Systems_TopLevel',
'/systems/infrastructure/de/de':'IBM_Systems_TopLevel',
'/systems/infrastructure/in/en':'IBM_Systems_TopLevel',
'/systems/infrastructure/jp/ja':'IBM_Systems_TopLevel',
'/systems/infrastructure/mx/es':'IBM_Systems_TopLevel',
'/systems/infrastructure/ru/ru':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/announcement.html':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/big-data-analytics':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/cloud-servers':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/it-infrastructure':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/software-defined-environment':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/software-defined-infrastructure':'IBM_Systems_TopLevel',
'/systems/it/i':'IBM_Systems_Hardware_Power',
'/systems/it/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/kr/power':'IBM_Systems_Hardware_Power',
'/systems/kr/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/kvm':'IBM_Systems_Hardware',
'/systems/mx/storage':'IBM_Systems_Hardware_Storage',
'/systems/pl/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/platformcomputing':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/products':'IBM_Systems_PlatformComputing',
'/systems/storage/spectrum/scale/index.html':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/products/hpc':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/products/lsf':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/products/mpi':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/products/symphony':'IBM_Systems_PlatformComputing',
'/systems/platformcomputing/solutions':'IBM_Systems_PlatformComputing',
'/systems/power':'IBM_Systems_Hardware_Power',
'/systems/power/advantages':'IBM_Systems_Hardware_Power',
'/systems/power/advantages/midsize.html':'IBM_Systems_Hardware_Power',
'/systems/power/announcement/index.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/520':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/710':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/710/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/710/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/740':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/740/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/750':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/750/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/750/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/770':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/770/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/775':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/780':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/780/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/795':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/795/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/benchmarks/erp.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/cbu':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/cod':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/cod/activations.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/cod/offerings.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e850':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e850/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e870':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e870/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e880':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e880/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/enterprise.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/hpc.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/linux.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/power6.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/reports/factsfeatures.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/reports/system_perf.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s812l-s822l':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s812l-s822l/browse.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s812l-s822l/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s814':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s814/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s822':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s822/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s822/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824l':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/scale-out.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/systemp.html':'IBM_Systems_Hardware_Power',
'/systems/power/migratetoibm':'IBM_Systems_Hardware_Power',
'/systems/power/migratetoibm/index.html':'IBM_Systems_Hardware_Power',
'/systems/power/announcement/index.html':'IBM_Systems_Hardware_Power',
'/systems/power/software':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/about.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/browsers':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/compatibility':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/expansionpack':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/alpha.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/altlic.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/date.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/download.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/support/release_strategy.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/sysmgmt/enterprise':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/upgrade':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v61':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v71':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v71/editions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v71/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/aix':'IBM_Systems_Hardware_Power',
'/systems/power/software/i':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/about.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/linux.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/linux/guide.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/linux_resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/mobile.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/resources/orderupgrade.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/solutions_support.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/web.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/dotnet.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/os.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/sp_history.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows_functions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows_resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows_sp.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows_support.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/webquery':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/http':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/iws':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator/web.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator/windows.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/netserver':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/swlisting.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox/downloads.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox/faq/jdbc.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/v7r1':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/virtualization.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/websphere':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux/powerkvm':'IBM_Systems_Hardware_Power',
'/systems/power/software/performance':'IBM_Systems_Hardware_Power',
'/systems/power/software/security':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization/editions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization-management':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/index.html':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/bigdata-analytics':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/bigdata-analytics/hadoop':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/cloud':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/cloud/openstack':'IBM_Systems_Hardware_Power',
'/systems/power/support':'IBM_Systems_Hardware_Power',
'/systems/power/support/perfmgmt':'IBM_Systems_Hardware_Power',
'/systems/ru/power':'IBM_Systems_Hardware_Power',
'/systems/ru/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/ru/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/services/labservices':'IBM_Systems_Hardware',
'/systems/storage':'IBM_Systems_Hardware_Storage',
'/systems/storage/announcement.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/data-storage-solutions.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/cisco-versastack.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/dcs3700':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/dcs3700/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/ds8000':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/ds8000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/ds8000/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/ds8000/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/exp2500':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700/browse.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v3700/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v5000':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v5000/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v5000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v5000/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/interop.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/xiv':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/xiv/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/xiv/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/xiv/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/840':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/840/product-details.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/840/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/900':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/900/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/900/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/900/technology.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v9000':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v9000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v9000/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/why-flash-storage-system.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/3592':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/3592/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_5':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_5/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_6':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_800gb':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_800gb/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/product':'IBM_Systems_Hardware_Storage',
'/systems/storage/product/interop.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-4':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-5':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-5/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san48b-5':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/entry':'IBM_Systems_Hardware_Storage',
'/systems/storage/software':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc/interop.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/cloud-storage.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/accelerate':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/storwize':'IBM_Systems_Hardware_Storage',
'/systems/storage/storwize/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/drives':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ltfs':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/oem/lto6/full-high':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/oem/lto6/full-high/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/oem/lto6/half-high':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1140':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1150':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1150/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2250':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2250/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2260':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2260/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2900':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2900/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3100':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3100/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3100/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3200':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3200/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3310':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3310/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3500':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3500/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts4500':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7620':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7650g':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7700':'IBM_Systems_Hardware_Storage',
'/systems/storage/virtualization':'IBM_Systems_Hardware_Storage',
'/systems/tw/power':'IBM_Systems_Hardware_Power',
'/systems/uk/platformcomputing/products/hpc/services':'IBM_Systems_Hardware_PlatformComputing',
'/systems/uk/power':'IBM_Systems_Hardware_Power',
'/systems/z':'IBM_Systems_Hardware_zSystems',
'/systems/z/advantages/gdps':'IBM_Systems_Hardware_zSystems',
'/systems/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/contest':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/contest/mea.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/skills_coursematerials.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/features/ziip':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/z13.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/z13_features.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/z13_specs.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/zenterprise/zbc12.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/zenterprise/zec12.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/news.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/solutions/ifl.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/racf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/sdsf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/sdsf/isf0tip.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/unix':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/unix/bpxa1p03.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/unix/ported/openssh':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/features/zosmf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/installation':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv/r13pdf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv/v1r13books.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv/v2r1pdf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv/zshelves13.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/library/bkserv/zswpdf':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/support':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/support/servicetest':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/tools/downloads':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/tools/java':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/tools/java/faq/javafaq.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zos/tools/java/products/jzos/overview.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zvse':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/zvse/documentation':'IBM_Systems_Hardware_zSystems',
'/systems/z/resources/swprice':'IBM_Systems_Hardware_zSystems',
'/systems/z/resources/swprice/mlc':'IBM_Systems_Hardware_zSystems',
'/systems/z/resources/swprice/reference/exhibits/hardware.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/resources/swprice/subcap/scrt':'IBM_Systems_Hardware_zSystems',
'/systems/z/solutions.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/solutions/cloud':'IBM_Systems_Hardware_zSystems',
'/de/cebit':'IBM_Events_TopLevel',
'/de/cebit/en':'IBM_Events_TopLevel',
'/de/events/breakfast':'IBM_Events_TopLevel',
'/innovation/uk/wimbledon/index.html':'IBM_Events_TopLevel',
'/se/vasaloppet':'IBM_Commerce_TopLevel',
'/za/storwize/et':'IBM_Systems_Hardware_Storage',
'/za/storwize/ke':'IBM_Systems_Hardware_Storage',
'/za/storwize/tz':'IBM_Systems_Hardware_Storage',
'/za/storwize/ug':'IBM_Systems_Hardware_Storage',
'/systems/jp/power':'IBM_Systems_Hardware_Power',
'/systems/jp/power/announcement.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/720':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s814':'IBM_Systems_Hardware_Power',
'/systems/jp/power/migratetoibm/whypower.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/aix':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/seminar':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/solutions/isite':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo/ptf/ptf.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo/ptf/service_pack.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/nmon':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/tlsp':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/virtual':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/console':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/lpar':'IBM_Systems_Hardware_Power',
'/systems/infrastructure/jp/ja':'IBM_Systems_Hardware',
'/systems/jp/storage':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/manual':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/media/dct/3580.shtml':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v3700/guide':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/flash':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape':'IBM_Systems_Hardware_Storage',
'/systems/jp/support.html':'IBM_Systems_Hardware',
'/systems/jp/uruu2015.html':'IBM_Systems_Hardware',
'/systems/jp/z':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/announcement':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/hardware/z13':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/os/zos':'IBM_Systems_Hardware_zSystems',
'/au/connect':'IBM_Events_Connect',
'/au/connect/agenda.html':'IBM_Events_Connect_Agenda',
'/au/connect/dates.html':'IBM_Events_Connect_Registration',
'/innovation/au/ausopen/mobile.html':'IBM_Commerce_TopLevel',
'/tw/dp-cs/smartbanking/overview':'IBM_Commerce_TopLevel',
'/software/howtobuy/passportadvantage/webenrollments/enrollments/enroll':'IBM_Commerce_TopLevel',
'/software/brandcatalog/puresystems/centre':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/brandcatalog/puresystems/centre/update':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/shopzseries/ShopzSeries_public.wss':'IBM_Commerce_TopLevel',
'/industries':'IBM_GBS_Services_Industries_Other',
'/industries/aerospacedefense':'IBM_GBS_Services_Industries_Other',
'/industries/automotive':'IBM_GBS_Services_Industries_Banking',
'/industries/banking':'IBM_GBS_Services_Industries_Banking',
'/industries/banking/core_banking.html':'IBM_GBS_Services_Industries_Banking',
'/industries/banking/customer_care.html':'IBM_GBS_Services_Industries_Other',
'/industries/communications':'IBM_GBS_Services_Industries_Other',
'/industries/education':'IBM_GBS_Services_Industries_Other',
'/industries/education/learning.html':'IBM_GBS_Services_Industries_Other',
'/industries/energy':'IBM_GBS_Services_Industries_Other',
'/industries/financialmarkets':'IBM_GBS_Services_Industries_Other',
'/industries/government':'IBM_GBS_Services_Industries_Other',
'/industries/healthcare':'IBM_GBS_Services_Industries_Healthcare',
'/industries/healthcare/ehealth.html':'IBM_GBS_Services_Industries_Healthcare',
'/industries/healthcare/health_management.html':'IBM_GBS_Services_Industries_Healthcare',
'/industries/insurance':'IBM_GBS_Services_Industries_Other',
'/industries/lifesciences':'IBM_GBS_Services_Industries_Other',
'/industries/mediaentertainment':'IBM_GBS_Services_Industries_Other',
'/industries/retail':'IBM_GBS_Services_Industries_Retail',
'/industries/traveltransportation':'IBM_GBS_Services_Industries_Other',
'/services/au/en/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/ca/en/gbs/consulting/careers/cbd/opportunities':'IBM_GBS_Services_Consulting_Careers',
'/services/cn/bcs':'IBM_GBS_Services_Consulting_Other',
'/services/cn/gbs/ais':'IBM_GBS_Services',
'/services/cn/gbs/alliances/oracle':'IBM_GBS_Services_Alliances',
'/services/cn/gbs/alliances/sap':'IBM_GBS_Services_Alliances',
'/services/cn/gbs/business-analytics/solutions':'IBM_GBS_Services',
'/services/cn/gbs/ibv':'IBM_GBS_Services',
'/services/cn/gbs/strategy':'IBM_GBS_Services_Consulting',
'/services/cn/zh/it-services/technical-support-services/index.html':'IBM_GTS_TechnicalSupportServices',
'/services/cn/zh/it-services/business-continuity':'IBM_GTS_Resiliency',
'/services/cn/zh/it-services/data-center':'IBM_GTS_Systems',
'/services/cn/zh/it-services/end-user-services.html':'IBM_GTS_Mobility',
'/services/cn/zh/it-services/enterprise-mobility':'IBM_GTS_Mobility',
'/services/cn/zh/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/cn/zh/it-services/technical-support-services':'IBM_GTS_TechnicalSupportServices',
'/services/de/de/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/de/gbs/consulting':'IBM_GBS_Services_Consulting',
'/services/fr/gbs/consulting':'IBM_GBS_Services_Consulting',
'/services/in/en/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/in/en/it-services/ibm-global-process-services.html':'IBM_GBS_Services',
'/services/in/en/it-services/networking-services/smart-grid-communications':'IBM_GTS_Networking',
'/services/in/en/it-services/server-services2.html':'IBM_GTS_Systems',
'/services/in/en/it-services/technical-support-services':'IBM_GTS_TechnicalSupportServices',
'/services/in/gbs/consulting':'IBM_GBS_Services_Consulting_Other',
'/services/jp/gbs/strategy':'IBM_GBS_Services_Strategy',
'/services/jp/gbs/consulting':'IBM_GBS_Services_Consulting',
'/services/jp/ja/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/jp/ja/it-services/technical-support-services/hardware-maintenance-services':'IBM_GTS_TechnicalSupportServices',
'/services/jp/ja/it-services/jp-of-its-maintenance-services__v008297p55842k19.html':'IBM_GTS_TechnicalSupportServices',
'/services/jp/ja/it-services/technical-support-services/servicepacs':'IBM_GTS_TechnicalSupportServices',
'/services/jp/ja/it-services/software-supportguide-center-contact.html':'IBM_GTS_TechnicalSupportServices',
'/services/jp/ja/it-services/svcline.html':'IBM_GTS_TechnicalSupportServices',
'/services/mx/es/it-services/business-continuity':'IBM_GTS_Resiliency',
'/services/mx/es/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/mx/es/it-services/technical-support-services/multivendor-it-support':'IBM_GTS_TechnicalSupportServices',
'/services/mx/gbs/ciso':'IBM_GBS_Services',
'/services/uk/en/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/us/business-consulting/bpo/ibm-bpo-services.html':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/bpo/ibm-global-process-services.html':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/bpo/recruitment-process-outsourcing.html':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/digital-operations-internetofthings':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/finance-transformation':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/interactiveexperience':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/sap-consulting':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/talent-change-management':'IBM_GBS_Services_Consulting_Other',
'/services/us/business-consulting/tech-data':'IBM_GBS_Services_Consulting_Other',
'/services/us/en/it-services/business-continuity':'IBM_GTS_Resiliency',
'/services/us/en/it-services/data-center':'IBM_GTS_Resiliency',
'/services/us/en/it-services/enterprise-mobility':'IBM_GTS_Mobility',
'/services/us/en/it-services/gts-it-service-home-page-1.html':'IBM_GTS_HomePage',
'/services/us/business-consulting/bpo/human-resources-and-learning.html':'IBM_GBS_Services_ITServices',
'/services/us/business-consulting/bpo/ibm-bpo-services.html':'IBM_GBS_Services_ITServices',
'/services/us/business-consulting/bpo/ibm-global-process-services.html':'IBM_GBS_Services_ITServices',
'/services/us/en/it-services/networking-services':'IBM_GTS_Networking',
'/services/us/business-consulting/bpo/recruitment-process-outsourcing.html':'IBM_GBS_Services',
'/services/us/en/it-services/systems':'IBM_GTS_Systems',
'/services/us/en/it-services/systems/managed-services':'IBM_GTS_Systems',
'/services/us/en/it-services/technical-support-services':'IBM_GTS_TechnicalSupportServices',
'/services/us/en/it-services/technical-support-services/hardware-maintenance-services':'IBM_GTS_TechnicalSupportServices',
'/services/us/en/it-services/technical-support-services/servicepacs':'IBM_GTS_TechnicalSupportServices',
'/services/us/en/it-services/technical-support-services/software-support-services':'IBM_GTS_TechnicalSupportServices',
'/services/us/en/it-services/technical-support-services/warranty-services-and-maintenance-options':'IBM_GTS_TechnicalSupportServices',
'/services/us/en/outsourcing':'IBM_GTS_Outsourcing',
'/services/us/gbs/ais':'IBM_GBS_Services',
'/services/us/gbs/application-management':'IBM_GBS_Services',
'/services/us/gbs/bus/html/bcs_careers.html':'IBM_GBS_Services_Careers',
'/services/us/gbs/bus/html/bcs_careers_opportunities.html':'IBM_GBS_Services_Careers',
'/services/us/gbs/bus/html/gbs-green-csr.html':'IBM_GBS_Services',
'/services/us/gbs/strategy':'IBM_GBS_Services_BusinessAnalytics',
'/services/us/gbs/strategy':'IBM_GBS_Services_BusinessAnalytics',
'/services/us/gbs/consulting':'IBM_GBS_Services_Consulting',
'/services/us/gbs/consulting/careers/cbd':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/cbd/opportunities.html':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/cbd/schools_events.html':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/cbd/summer_internships.html':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/consultingbydegrees':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/consultingbydegrees/opportunities':'IBM_GBS_Services_Consulting_Careers',
'/services/us/gbs/consulting/careers/mbagrad':'IBM_GBS_Services_Consulting_Careers',
'/services/us/business-consulting/interactiveexperience':'IBM_GBS_Services',
'/services/us/gbs/mobile-consulting':'IBM_GBS_Services',
'/services/us/gbs/strategy':'IBM_GBS_Services_Other_Strategy',
'/services/us/gbs/strategy/business-strategy.html':'IBM_GBS_Services_Other_Strategy',
'/services/us/gbs/strategy/market-and-customer-management.html':'IBM_GBS_Services_Other_Strategy',
'/services/us/gbs/strategy/mna':'IBM_GBS_Services_Other_Strategy',
'/services/us/gbs/thoughtleadership':'IBM_GBS_Services_ThoughtLeadership',
'/services/us/gbs/thoughtleadership/auto2025':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/big-data-banking':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/cognitivefuture':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/ibv-digital-transformation.html':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/ibv-social-crm-whitepaper.html':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/internetofthings':'IBM_GBS_Services_ThoughtLeadership_Other',
'/services/us/gbs/thoughtleadership/millennialworkplace':'IBM_GBS_Services_ThoughtLeadership_Other',
'/systems/support':'IBM_Systems_Hardware',
'/events/ast/fsc/15frontiers.nsf':'IBM_Events_TopLevel',
'/events/global/interconnect/sessions':'IBM_Events_Interconnect_Sessions',
'/events/tools/insight/2015ems':'IBM_Events_Insight_Speakers',
'/events/tools/insight/2015ems/login.xhtml':'IBM_Events_Insight_LogIn',
'/events/tools/insight/2015ems/login.xhtml':'IBM_Events_Insight',
'/events/tools/interconnect/2015ems':'IBM_Events_Interconnect',
'/events/tools/interconnect/2015ems/login.xhtml':'IBM_Events_Interconnect_Login',
'/events/tools/interconnect/2015ems/screens/speakers/checklist.xhtml':'IBM_Events_Interconnect_Speakers',
'/events/tools/interconnect/2015ems/speaker/kit.xhtml':'IBM_Events_Interconnect_Speakers',
'/events/wwe/edge/edge15reg.nsf/reg':'IBM_Events_Edge',
'/events/wwe/insight/insight15reg.nsf/reg':'IBM_Events_Insight_Registration',
'/events/wwe/interconnect/ic2015faq.nsf/faq':'IBM_Events_Insight_FAQ',
'/events/wwe/interconnect/ic2015faq.nsf/wqav17':'IBM_Events_Interconnect_FAQ',
'/events/wwe/interconnect/ic2015reg.nsf/reg':'IBM_Events_Interconnect_Registration',
'/ibm/puresystems/us/en/big-data':'IBM_Systems_Middleware',
'/ibm/puresystems/us/en/cloud':'IBM_Systems_Middleware',
'/middleware/integration/en-us/api-economy.html':'IBM_Systems_Middleware',
'/software/au/websphere':'IBM_Systems_Middleware_Integration',
'/software/awdtools/deployment':'IBM_Systems_Middleware',
'/software/be/websphere':'IBM_Systems_Middleware_Integration',
'/software/brandcatalog/puresystems/centre/details':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/ca/en/websphere':'IBM_Systems_Middleware_Integration',
'/mobilefirst/cn/zh':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/cn/businessagility/products/mq.html':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/cn/solutions/soa':'IBM_Systems_Middleware_Integration',
'/software/collaboration/digitalexperience/gartner-reports':'IBM_Systems_Middleware_DigitalExperience',
'/software/collaboration/digitalexperience/solutions':'IBM_Systems_Middleware_DigitalExperience',
'/software/decision-management':'IBM_Systems_Middleware_SmarterProcess',
'/software/decision-management/operational-decision-management/scenarios':'IBM_Systems_Middleware_SmarterProcess',
'/software/es/solutions/soa':'IBM_Systems_Middleware_Integration',
'/software/es/websphere':'IBM_Systems_Middleware_Integration',
'/software/fr/websphere':'IBM_Systems_Middleware_Integration',
'/software/htp/cics/openbeta':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/htp/tpf/alcs':'IBM_Systems_Middleware',
'/software/info/bpm/industry/banking':'IBM_Systems_Middleware_SmarterProcess',
'/software/info/itsolutions/enterprisearchitecture':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/info/pureapplication/system-options.html':'IBM_Systems_Middleware',
'/software/integration/wid':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/integration/wmq/v7':'IBM_Systems_Middleware_Integration',
'/software/integration/wmq/wmq-library':'IBM_Systems_Middleware_Integration',
'/software/it/ibmblueworkslive':'IBM_Systems_Middleware_SmarterProcess',
'/software/it/websphere':'IBM_Systems_Middleware_Integration',
'/software/jp/cmp/pureapplication':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/mx/websphere':'IBM_Systems_Middleware_Integration',
'/software/nl/websphere':'IBM_Systems_Middleware_Integration',
'/software/pl/solutions/soa':'IBM_Systems_Middleware_Integration',
'/software/pl/websphere':'IBM_Systems_Middleware_Integration',
'/middleware/smarter-process/en-us':'IBM_Systems_Middleware_SmarterProcess',
'/software/solutions/soa/faqs.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/gov/lifecycle':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/newsletter/june10/advanced_case_management.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/newsletter/june12/top_ten.html':'IBM_Systems_Middleware_Integration',
'/software/solutions/soa/offerings.html':'IBM_Systems_Middleware_Integration',
'/software/tivoli/csi/backup-recovery/resources.html':'IBM_Systems_Middleware',
'/software/tivoli/optimizing-itsm':'IBM_Systems_Middleware_ITSvcManagement',
'/software/tw/websphere':'IBM_Systems_Middleware_Integration',
'/software/voice':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/webservers/appserv/was/tools':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/webservers/appserv/was/trials':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/websphere/announcement050310.html':'IBM_Systems_Middleware_Integration',
'/software/websphere/industry/banking/risk-fraud-management':'IBM_Systems_Middleware_Integration',
'/software/products/de/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/de/appserv-was-liberty-core':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/de/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/de/category/bpm-software':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/de/category/it-service-desk':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/de/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/de/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/en/adapters':'IBM_Systems_Middleware_Integration_WebSphereSOA',
'/software/products/en/apa':'IBM_Systems_Middleware_ApplicationPlatform_WebSphereTraditionalTools',
'/software/products/en/api-management-family':'IBM_Systems_Middleware_Integration_WebSphereAPIManagement',
'/software/products/en/apl2':'IBM_Systems_Middleware',
'/software/products/en/application-performance-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/appserv-washyper':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/assetmanagementforit':'IBM_Systems_Middleware',
'/software/products/en/business-process-manager-express':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/business-process-manager-tools':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/cast-iron-hypervisor':'IBM_Systems_Middleware_Integration',
'/software/products/en/cast-iron-live':'IBM_Systems_Middleware_Integration',
'/software/products/en/category/application-foundation':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/category/business-monitoring':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/category/cloud-application-virtualization':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/category/integration-foundation':'IBM_Systems_Middleware_Integration',
'/software/products/en/category/it-operations-analytics':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/category/it-service-desk':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/category/messaging':'IBM_Systems_Middleware_Integration',
'/software/products/en/category/network-management':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/category/process-automation-integrity':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/category/transaction-processing':'IBM_Systems_Middleware_Integration',
'/software/products/en/cics-tools':'IBM_Systems_Middleware_ApplicationPlatform_WebSphereTraditionalTools',
'/software/products/en/cobolaix':'IBM_Systems_Middleware',
'/software/products/en/compositeapplicationmanagerforapplications':'IBM_Systems_Middleware',
'/software/products/en/compositeapplicationmanagerfortransactions':'IBM_Systems_Middleware',
'/software/products/en/datapower-gateway-virtual':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/products/en/datapower-xc10':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/info/app/ecatalog/index.html':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/products/en/debugtool':'IBM_Systems_Middleware_ApplicationPlatform_WebSphereTraditionalTools',
'/software/products/en/digital-experience-patterns':'IBM_Systems_Middleware_DigitalExperience',
'/software/products/en/employee-experience-suite':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/filemanager':'IBM_Systems_Middleware_ApplicationPlatform_WebSphereTraditionalTools',
'/software/products/en/fortcompfami':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/hostaccess':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/ibm-mq-advanced-for-developers':'IBM_Systems_Middleware_Integration',
'/software/products/en/ibm-mq-advanced-message-security':'IBM_Systems_Middleware_Integration',
'/software/products/en/ibm-mq-light':'IBM_Systems_Middleware_Integration',
'/software/products/en/ibm-mq-managed-file-transfer':'IBM_Systems_Middleware_Integration',
'/software/products/en/ibm-operational-decision-manager-advanced':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/en/ibm-operations-analytics---log-analysis':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/ibm-operations-analytics---predictive-insights':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/ibmservdelimana':'IBM_Systems_Middleware',
'/software/products/en/ibmsmarmoni':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/ibmtivolinetworkmanageripedition':'IBM_Systems_Middleware',
'/software/products/en/ibm-workload-automation':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/integration-bus-express':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/en/integration-bus-healthcare-pack':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/en/integration-bus-standard':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/en/modeler-advanced':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/en/osgi':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/plicompfami':'IBM_Systems_Middleware',
'/software/products/en/portalexpress':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/raa':'IBM_Systems_Middleware_ApplicationPlatform_TraditionalALM',
'/software/products/en/ratiassemanafami':'IBM_Systems_Middleware_ApplicationPlatform_TraditionalALM',
'/software/products/en/rds':'IBM_Systems_Middleware_ApplicationPlatform_Compilers',
'/software/products/en/real-time':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/en/serviceautomationmanager':'IBM_Systems_Middleware',
'/software/products/en/smartcloud-cost-management':'IBM_Systems_Middleware',
'/software/products/en/tivoli-business-service-manager':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/tivolicontinuousdataprotectionforfiles':'IBM_Systems_Middleware',
'/software/products/en/tivoli-network-manager-family':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/tivoli-omegamon-xe-product-line':'IBM_Systems_Middleware',
'/software/products/en/tivoli-system-automation':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/tivonetc':'IBM_Systems_Middleware',
'/software/products/en/tivonetcconfmana':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/tivosystautoformult':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/en/txseries':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/was-featurepacks':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/web2mobile':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/en/websportenab':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/en/workload-scheduler':'IBM_Systems_Middleware',
'/software/products/en/xlcpp-linux':'IBM_Systems_Middleware_ApplicationPlatform_Compilers',
'/software/products/es/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/es/http-servers':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/es/ibm-datapower-gateways':'IBM_Systems_Middleware_Integration_WebSphereDatapower',
'/software/products/es/ibm-integration-bus':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/es/pcomm':'IBM_Systems_Middleware_Integration',
'/software/products/es/portalserver':'IBM_Systems_Middleware_DigitalExperience_Portal',
'/software/products/es/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/es/websphere-mq':'IBM_Systems_Middleware_Integration',
'/software/products/es/wsesb':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/fr/category/bpm-software':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/fr/ibm-integration-bus':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/fr/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/fr/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/it/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/ja/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/ja/category/bpm-software':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/ja/http-servers':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/products/ja/ibm-integration-bus':'IBM_Systems_Middleware_Integration_WebSphereMessageBroker',
'/software/products/ja/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/ja/ibmtivolinetcoolomnibus':'IBM_Systems_Middleware_ITSvcManagement',
'/software/products/ja/licensemetrictool':'IBM_Systems_Middleware',
'/software/products/ja/pcomm':'IBM_Systems_Middleware_Integration',
'/software/products/ja/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/ko/api-management':'IBM_Systems_Middleware_Integration_WebSphereAPIManagement',
'/software/products/pt/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/pt/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/pt/category/bpm-software':'IBM_Systems_Middleware_SmarterProcess',
'/software/products/pt/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/products/pt/rtc':'IBM_Systems_Middleware_ApplicationPlatform_CLM',
'/software/products/ru/appserv-was':'IBM_Systems_Middleware_ApplicationPlatform_Servers',
'/software/products/ru/business-process-manager-family':'IBM_Systems_Middleware_SmarterProcess_WebSphereWBI',
'/software/products/ru/ibm-mq':'IBM_Systems_Middleware_Integration',
'/software/brandcatalog/puresystems/centre/browse':'IBM_Systems_Middleware_ApplicationPlatform',
'/software/brandcatalog/puresystems/centre/details':'IBM_Systems_Middleware_ApplicationPlatform',
'/mainframe50':'IBM_Systems_Hardware_zSystems',
'/software/data/db2imstools/products/ims-tools.html':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/connect':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/downloads.html':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/enterprise-suite':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/imscc':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/products.html':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/resources.html':'IBM_Systems_Hardware_zSystems',
'/software/data/ims/usergroups.html':'IBM_Systems_Hardware_zSystems',
'/software/htp/cics/ibmexplforzos':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/apps':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/badw':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/data':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/infra':'IBM_Systems_Hardware_zSystems',
'/software/os/systemz/itsm':'IBM_Systems_Hardware_zSystems',
'/software/products/en/appserv-waszos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/cics-ctg-zos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/cics-tserver-zos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/czos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/fastbackforworkstations':'IBM_Systems_Hardware_Storage',
'/software/products/en/ims-product':'IBM_Systems_Hardware_zSystems',
'/software/products/en/plizos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/tivoli-netview-zos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/tivolistoragemanagerhsmforwindows':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivostormanafordata':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivostormanaforenteresoplan':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivostormanaformail':'IBM_Systems_Hardware_Storage',
'/software/products/en/tivosystautoforzos':'IBM_Systems_Hardware_zSystems',
'/software/products/en/wmq-zos':'IBM_Systems_Hardware_zSystems',
'/software/products/fr/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/pt/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/ru/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/software/products/zh/spectrum-protect':'IBM_Systems_Hardware_Storage',
'/systems/au/power':'IBM_Systems_Hardware_Power',
'/systems/au/storage':'IBM_Systems_Hardware_Storage',
'/systems/au/z':'IBM_Systems_Hardware_zSystems',
'/systems/be/storage/solutions':'IBM_Systems_Hardware_Storage',
'/systems/br/power':'IBM_Systems_Hardware_Power',
'/systems/br/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/br/power/migratetoibm/whypower.html':'IBM_Systems_Hardware_Power',
'/systems/br/storage':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/disk/storwize_v3700/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/disk/storwize_v7000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/br/storage/tape/ts3100':'IBM_Systems_Hardware_Storage',
'/systems/br/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/br/z/concursomainframe':'IBM_Systems_Hardware_zSystems',
'/systems/ca/en/servers/power':'IBM_Systems_Hardware_Power',
'/systems/clusters':'IBM_Systems_Hardware',
'/systems/clusters/hardware.html':'IBM_Systems_Hardware',
'/systems/infrastructure/cn/zh/index.shtml':'IBM_Systems_Hardware',
'/systems/cn/ads/2012q4_flexsystem.shtml':'IBM_Systems_Hardware',
'/systems/infrastructure/cn/zh':'IBM_Systems_Hardware',
'/systems/infrastructure/cn/zh':'IBM_Systems_Hardware',
'/systems/cn/management/serverguide.shtml':'IBM_Systems_Hardware',
'/systems/cn/platformcomputing/products/clustermanager':'IBM_Systems_Hardware_PlatformComputing',
'/systems/cn/platformcomputing/products/hpc':'IBM_Systems_Hardware_PlatformComputing',
'/systems/cn/power/hardware/720':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/740':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/740/specs.shtml':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/750':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/750/specs.shtml':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/780':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/780/specs.shtml':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/e870':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/e880':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/s812l-s822l':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/s822':'IBM_Systems_Hardware_Power',
'/systems/cn/power/hardware/s824':'IBM_Systems_Hardware_Power',
'/systems/cn/power/library':'IBM_Systems_Hardware_Power',
'/systems/cn/power/software':'IBM_Systems_Hardware_Power',
'/systems/cn/power/software/aix':'IBM_Systems_Hardware_Power',
'/systems/cn/power/software/aix/v71':'IBM_Systems_Hardware_Power',
'/systems/cn/power/software/virtualization':'IBM_Systems_Hardware_Power',
'/systems/cn/power/solutions':'IBM_Systems_Hardware_Power',
'/systems/cn/storage/disk/index.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/index.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/index.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/ds8000/overview.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3500/features.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3500/specifications.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3700/features.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v3700/specs.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v5000':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/storwize_v7000/specifications.shtml':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/disk/xiv':'IBM_Systems_Hardware_Storage',
'/systems/cn/storage/virtualisation':'IBM_Systems_Hardware_Storage',
'/Lenovo_Series_List.aspx?CategoryCode=A31B01':'IBM_Systems_Hardware',
'/systems/cn/z/hardware/z13.shtml':'IBM_Systems_Hardware_zSystems',
'/systems/cz/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/data/flash/sa/migratetoibm':'IBM_Systems_Hardware',
'/systems/data/flash/storage/disk/storwize_3700/12-disk':'IBM_Systems_Hardware_Storage',
'/systems/data/flash/storage/disk/storwize_3700/24-disk':'IBM_Systems_Hardware_Storage',
'/systems/data/flash/storage/disk/storwize_v7000/demo':'IBM_Systems_Hardware_Storage',
'/systems/infrastructure/de/de':'IBM_Systems_Hardware',
'/systems/de/i':'IBM_Systems_Hardware_Power',
'/systems/de/platformcomputing':'IBM_Systems_Hardware_PlatformComputing',
'/systems/de/power/announcement.html':'IBM_Systems_Hardware_Power',
'/systems/de/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/de/power/migratetoibm':'IBM_Systems_Hardware_Power',
'/systems/de/power/software/i':'IBM_Systems_Hardware_Power',
'/systems/de/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/de/storage':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/san':'IBM_Systems_Hardware_Storage',
'/systems/infrastructure/de/de':'IBM_Systems_Hardware_Storage',
'/systems/infrastructure/de/de':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/de/storage/tape/ts3100':'IBM_Systems_Hardware_Storage',
'/systems/de/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/director/downloads/v52.html':'IBM_Systems_Hardware',
'/systems/director/editions/express.html':'IBM_Systems_Hardware',
'/systems/director/resources.html':'IBM_Systems_Hardware',
'/systems/edge/certifications.html':'IBM_Systems_Hardware',
'/systems/enterprise':'IBM_Systems_Hardware',
'/systems/es/power':'IBM_Systems_Hardware_Power',
'/systems/es/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/es/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/es/z':'IBM_Systems_Hardware_zSystems',
'/systems/flex/compute-nodes/power':'IBM_Systems_Hardware',
'/systems/flex/compute-nodes/power/p260-p460':'IBM_Systems_Hardware',
'/systems/flex/compute-nodes/power/p260-p460/specs.html':'IBM_Systems_Hardware',
'/systems/flex/storage/v7000/features.html':'IBM_Systems_Hardware',
'/systems/flex/storage/v7000/specs.html':'IBM_Systems_Hardware',
'/systems/fr/events/modernisation_i_2015':'IBM_Systems_Hardware',
'/systems/fr/i':'IBM_Systems_Hardware_Power',
'/systems/fr/platformcomputing':'IBM_Systems_Hardware_PlatformComputing',
'/systems/fr/power/announcement.html':'IBM_Systems_Hardware_Power',
'/systems/fr/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/fr/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/fr/storage':'IBM_Systems_Hardware_Storage',
'/systems/fr/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/fr/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/fr/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/fr/storage/solutions.html':'IBM_Systems_Hardware_Storage',
'/systems/fr/tarifibm':'IBM_Systems_Hardware',
'/systems/fr/z/solutions/data.html':'IBM_Systems_Hardware_zSystems',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/infrastructure/us/en':'IBM_Systems_Hardware',
'/systems/hk/storage/disk/storwize_v7000/offer.html':'IBM_Systems_Hardware_Storage',
'/systems/hr/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/infrastructure/in/en':'IBM_Systems_Hardware',
'/systems/in/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/in/power/software/aix':'IBM_Systems_Hardware_Power',
'/systems/in/power/software/aix/v71':'IBM_Systems_Hardware_Power',
'/systems/in/power/software/i':'IBM_Systems_Hardware_Power',
'/systems/in/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/disk/storwize_v3700/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/disk/storwize_v7000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/improve-agility':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/network':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/product':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/storwize':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/tape/ts3100':'IBM_Systems_Hardware_Storage',
'/systems/in/storage/tape/ts3500':'IBM_Systems_Hardware_Storage',
'/systems/in/xoffers/san.html':'IBM_Systems_Hardware',
'/systems/infrastructure/br/pt/it-infrastructure':'IBM_Systems_TopLevel',
'/systems/infrastructure/ca/en':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/it-infrastructure':'IBM_Systems_TopLevel',
'/systems/infrastructure/cn/zh/software-defined-environment':'IBM_Systems_TopLevel',
'/systems/infrastructure/es/es':'IBM_Systems_TopLevel',
'/systems/infrastructure/fr/fr':'IBM_Systems_TopLevel',
'/systems/infrastructure/it/it':'IBM_Systems_TopLevel',
'/systems/infrastructure/jp/ja/cloud-servers':'IBM_Systems_TopLevel',
'/systems/infrastructure/jp/ja/it-infrastructure':'IBM_Systems_TopLevel',
'/systems/infrastructure/kr/ko':'IBM_Systems_TopLevel',
'/systems/infrastructure/tw/zh':'IBM_Systems_TopLevel',
'/systems/infrastructure/tw/zh/infrastructurefacts.html':'IBM_Systems_TopLevel',
'/systems/infrastructure/uk/en':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/it-infrastructure-matters':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/it-infrastructure-matters/it-infrastructure-report.html':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/mobile-it':'IBM_Systems_TopLevel',
'/systems/infrastructure/us/en/technical-breakthroughs/stream-processing.html':'IBM_Systems_TopLevel',
'/systems/intellistation':'IBM_Systems_Hardware',
'/systems/it/power':'IBM_Systems_Hardware_Power',
'/systems/it/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/it/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/it/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/it/z':'IBM_Systems_Hardware_zSystems',
'/systems/infrastructure/kr/ko':'IBM_Systems_Hardware',
'/systems/kr/power/hardware':'IBM_Systems_Hardware_Power',
'/systems/kr/power/power8prod.html':'IBM_Systems_Hardware_Power',
'/systems/kr/power/software/aix/v71':'IBM_Systems_Hardware_Power',
'/systems/kr/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/kr/storage':'IBM_Systems_Hardware_Storage',
'/systems/kr/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/kr/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/kr/storage/network':'IBM_Systems_Hardware_Storage',
'/systems/kr/storage/whyibm':'IBM_Systems_Hardware_Storage',
'/systems/kr/z':'IBM_Systems_Hardware_zSystems',
'/systems/kvm/whykvm.html':'IBM_Systems_Hardware',
'/systems/mx/power':'IBM_Systems_Hardware_Power',
'/systems/mx/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/mx/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/nl/storage/solutions':'IBM_Systems_Hardware_Storage',
'/systems/nl/z':'IBM_Systems_Hardware_zSystems',
'/systems/pl/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/platformcomputing/products/clustermanager':'IBM_Systems_Hardware_PlatformComputing',
'/systems/platformcomputing/products/lsf/processmanager.html':'IBM_Systems_Hardware_PlatformComputing',
'/systems/platformcomputing/products/symphony/highperfhadoop.html':'IBM_Systems_Hardware_PlatformComputing',
'/systems/platformcomputing/solutions/hpccloud.html':'IBM_Systems_Hardware_PlatformComputing',
'/systems/power/advantages/power.html':'IBM_Systems_Hardware_Power',
'/systems/power/advantages/why-power-systems.html':'IBM_Systems_Hardware_Power',
'/systems/power/community':'IBM_Systems_Hardware_Power',
'/systems/power/education':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/550':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/570':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/595':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/720/upgrades.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/730':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/730/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/740/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/740/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/750/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/760':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/770/browse.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/770/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/770/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/795/browse.html':'IBM_Systems_Hardware_Power',
'/common/ssi/cgi-bin/ssialias?infotype=PM&subtype=RG&appname=STGE_PO_PO_USEN&htmlfid=POO03017USEN':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/benchmarks/hpc.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/cbu/register.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e850/browse.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e850/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e850/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e870/browse.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e870/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e870/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e880/browse.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e880/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/e880/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/eserverp5/entry/520':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/eserverp5/entry/550':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/notices/cpw.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/notices/rperf.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/peripherals':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/peripherals/flash':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/pseries/highend/p690':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/purepower':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/reports/quick-reference.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s812l-s822l/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s814/browse_aix.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s814/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s814/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s822/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s822/perfdata.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/s824l/specs.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/sod.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/solutioneditions/ibmi':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/systemi.html':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/systemi/520':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/systemp/highend/595':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/systempools':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/vouchers':'IBM_Systems_Hardware_Power',
'/systems/power/hardware/whitepapers/am_exp.html':'IBM_Systems_Hardware_Power',
'/systems/power/resources':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/certifications':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/ezinstall.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/linux/toolbox/rpmgroups.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/robust.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/service.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/sysmgmt/wpar/v53_wpar.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v53':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v61/editions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/aix/v61/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/aix/enterprise.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/aix/faq_support.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/aix/standard.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/i':'IBM_Systems_Hardware_Power',
'/systems/power/software/availability/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/resources/connections.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/resources/dvd.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/resources/endservice.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/solutions_resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/support.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/web_resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/web_sp.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/apars.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/console.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/toolkit':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/access/windows/toolkit/database.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/docs/books.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/products/sql-development-kit.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/support/code/sql-guide-cobol.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/support/tips/odbcfaq.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/webquery/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/webquery/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/db2/webquery/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/iwas':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/iws/gettingstarted.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/jscheduler':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/ldap':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/licensing.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/management.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/management/performance/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/management/performance/tools.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator/plugins.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/navigator/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/netserver/gonets.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/php':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/print/infoprint-server':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/strategy.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox/faq':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox/faq/ports.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/toolbox/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/v6r1':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/v7r1/conversion.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/v7r1/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/virtualization':'IBM_Systems_Hardware_Power',
'/systems/power/software/i/websphere/v855':'IBM_Systems_Hardware_Power',
'/systems/power/software/ilmt.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux/powerkvm/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/linux/solutions.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/management':'IBM_Systems_Hardware_Power',
'/systems/power/software/performance/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/security/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization/enterprise.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization/standard.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization-management/features.html':'IBM_Systems_Hardware_Power',
'/systems/power/software/virtualization-management/resources.html':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/developers':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/open-platform.html':'IBM_Systems_Hardware_Power',
'/systems/power/solutions/watson':'IBM_Systems_Hardware_Power',
'/systems/power/success':'IBM_Systems_Hardware_Power',
'/systems/power/support/powercare':'IBM_Systems_Hardware_Power',
'/systems/ru/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/ru/storage':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/data-storage-solutions.html':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/disk/ds3500':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/disk/storwize_v7000/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/san':'IBM_Systems_Hardware_Storage',
'/systems/ru/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/ru/z':'IBM_Systems_Hardware_zSystems',
'/systems/se/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/services/briefingcenter':'IBM_Systems_Hardware',
'/systems/services/briefingcenter/poughkeepsie':'IBM_Systems_Hardware',
'/systems/services/briefingcenter/rtpx/planning.html':'IBM_Systems_Hardware',
'/systems/services/labservices/platforms/labservices_power.html':'IBM_Systems_Hardware',
'/systems/sg/power/linuxonpower/analytics.html':'IBM_Systems_Hardware_Power',
'/systems/sg/power/linuxonpower/cloud.html':'IBM_Systems_Hardware_Power',
'/systems/si/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/storage/disk/dcs3700/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/dcs3860':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/dcs3860/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/ds8000/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/exp2500/browse.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/exp2500/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/exp3000':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/storwize_v7000/reviews.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/disk/xiv/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/720-820':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/900/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/abouttime_overviewinfographic.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/ecosystem':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/tms-acquisition.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/transform.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v840':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v840/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v840/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/flash/v9000/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/3592/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/distributors':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_400GB/index.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_5/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_6/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_6/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/lto_800gb/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/tape_compatibility.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/media/warranty':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale':'IBM_Systems_Hardware_Storage',
'/systems/storage/news':'IBM_Systems_Hardware_Storage',
'/systems/storage/product/mainframe-storage.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/product/power.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/product/x.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/resource':'IBM_Systems_Hardware_Storage',
'/systems/storage/resource/info/compression-guarantee.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/na':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san06b-r':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-4/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-4/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san24b-5/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san48b-5/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san48b-5/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san768b-2':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san768b-2/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/b-type/san96b-5':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/enterprise':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/midrange':'IBM_Systems_Hardware_Storage',
'/systems/storage/san/why':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/smartcloud-storage-access':'IBM_Systems_Hardware_Storage',
'/systems/storage/software/virtualization/svc/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/backup-and-disaster-recovery':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/data_encryption':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/deduplication':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/flash-storage-vs-ssd':'IBM_Systems_Hardware_Storage',
'/systems/storage/solutions/isv':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/accelerate/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/ess':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/spectrum/scale/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/storwize/resources.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/storwize/what-is-storwize-virtualized-storage.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/automation':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ltfs/downloads.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ltfs/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/oem/lto6/full-high/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/oem/lto6/half-high/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1130':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1140/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts1150/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2230':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2240':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2260/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2360':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts2900/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3100/browse.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3200/browse.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3200/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3310/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3500/browse.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts3500/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts4500/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts4500/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7226':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7620/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7650g/features.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7650g/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/storage/tape/ts7700/specifications.html':'IBM_Systems_Hardware_Storage',
'/systems/tr/storage':'IBM_Systems_Hardware_Storage',
'/systems/tw/promotion/hot':'IBM_Systems_Hardware',
'/systems/tw/promotion/hot/system.html':'IBM_Systems_Hardware',
'/systems/tw/storage':'IBM_Systems_Hardware_Storage',
'/systems/tw/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/tw/z':'IBM_Systems_Hardware_zSystems',
'/systems/uk/event/flashrealisedlondon':'IBM_Systems_Hardware',
'/systems/uk/platformcomputing':'IBM_Systems_Hardware_PlatformComputing',
'/systems/uk/platformcomputing/spectrum':'IBM_Systems_Hardware_PlatformComputing',
'/systems/uk/power/migratetoibm/whypower.html':'IBM_Systems_Hardware_Power',
'/systems/uk/storage':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/disk':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/disk/storwize_v3700':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/disk/storwize_v7000':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/flash':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/solutions':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/uk/storage/tape':'IBM_Systems_Hardware_Storage',
'/systems/uk/z':'IBM_Systems_Hardware_zSystems',
'/systems/uk/z/announcement.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/advantages':'IBM_Systems_Hardware_zSystems',
'/systems/z/education':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/contest/brazil.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/contest/china.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/contest/dach.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/masterthemainframe/highlights':'IBM_Systems_Hardware_zSystems',
'/systems/z/education/academic/schools_na.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/connectivity/products/fe8.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/features/zaap':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/zenterprise/zbc12_specs.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/zenterprise/zbx.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/hardware/zenterprise/zec12_specs.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/about.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/linux-one.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/linux-one.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/resources/testedplatforms.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/resources':'IBM_Systems_Hardware_zSystems',
'/systems/z/software':'IBM_Systems_Hardware_zSystems',
'/systems/z/os/linux/linux-one.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/solutions/data.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/solutions/security.html':'IBM_Systems_Hardware_zSystems',
'/systems/z/solutions/security_subintegrity.html':'IBM_Systems_Hardware_zSystems',
'/systems/za/storage/flash/why-flash-storage-system.html':'IBM_Systems_Hardware_Storage',
'/press/us/en/pressrelease/47241.wss':'IBM_Systems_Hardware',
'/press/us/en/pressrelease/47241.wss':'IBM_Systems_Hardware',
'/systems/jp/photo':'IBM_Systems_Hardware',
'/systems/jp/photo/power':'IBM_Systems_Hardware',
'/systems/jp/power/hardware/710':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/720/specs.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/750':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/e850':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/e870':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s812l-s822l':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s814/specs.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s822':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s824':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/s824/specs.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/hardware/scale-out.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/aix/7':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/aix/overview':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/library':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/os':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/software/db2/webquery':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/software/db2/webquery/resources.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/solutions':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo/i5os.shtml':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo/i-access_win7.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/i/techinfo/ptf/pcomm.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/software/linux':'IBM_Systems_Hardware_Power',
'/systems/jp/power/solutions':'IBM_Systems_Hardware_Power',
'/systems/jp/power/solutions/watson':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/gpfs':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/hacmp':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/hmc':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/introduction.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/lvm/intro.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/aix/nim':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/console/asmi.html':'IBM_Systems_Hardware_Power',
'/systems/jp/power/techinfo/powervm':'IBM_Systems_Hardware_Power',
'/systems/jp/power/userref':'IBM_Systems_Hardware_Power',
'/systems/jp/software/director':'IBM_Systems_Hardware',
'/systems/jp/software/director/downloads':'IBM_Systems_Hardware',
'/systems/jp/storage/column/backup/01.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/column/backup/02.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/media/dct/4mm.shtml':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/media/top.shtml':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/ds8000/ds8000.shtml':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v3700/features.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v3700/specs.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v5000':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v7000/features.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v7000/guide':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/storwize_v7000/specs.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/xiv':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/disk/xiv/specs.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/flash/900':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape/2260':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape/2900':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape/3100':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape/3100/specs.shtml':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/tape/3200':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/products/virtualization/svc':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/san/switch.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/spectrum':'IBM_Systems_Hardware_Storage',
'/systems/jp/storage/spectrum/overview.html':'IBM_Systems_Hardware_Storage',
'/systems/jp/uruu2015_2.html':'IBM_Systems_Hardware',
'/systems/jp/z/hardware':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/hardware/z13/features.html':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/hardware/z13/specs.html':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/masterthemainframe':'IBM_Systems_Hardware_zSystems',
'/systems/jp/z/why_z':'IBM_Systems_Hardware_zSystems',
'/software/shopzseries/shopzseries.wss':'IBM_Systems_Hardware_zSystems',
'/software/shopzseries/shopzserieshelp.wss':'IBM_Systems_Hardware_zSystems',
'/software/shopzseries/shopzserieshelp_public.wss':'IBM_Systems_Hardware_zSystems'
};

for(var ibmCMSiteURL in ibmCMURLMatch) {
 var ibmCMCoreTag = ibmCMURLMatch[ibmCMSiteURL];
  if((ibmCMSiteURL === ibmCMURLPathname) || (ibmCMSiteURL + '/' === ibmCMURLPathname)) {

   var ibmCMSMeta = document.getElementsByTagName("meta");
   for (var i = 0; i < ibmCMSMeta.length; i++) {
    if ((ibmCMSMeta[i].name.toLowerCase() == "ibm.wtmsite") || (ibmCMSMeta[i].name.toLowerCase() == "ibm.wtmcategory")){
     ibmCMSMeta[i].content = '';
    }
   }

if (String(document.cookie).match(/(^| )(w3ibmProfile|w3_sauid|PD-W3-SSO-|OSCw3Session|IBM_W3SSO_ACCESS)=/)) {
 var ibmCMCoreTagSplitSecond = 'New_IBMER';
}
else {
 var ibmCMCoreTagSplitSecond = 'New_IBM_' + ibmCMCoreTag.split("_")[1];
}

   digitalData = {
    page: {
     pageInfo: {
      ibm: {
       siteID: ibmCMCoreTagSplitSecond
      }
     },
     category: {
      primaryCategory: ibmCMCoreTag
     }
    }
   };
  }
}
}());

(function() {
    var ghostFunctions = [
            'cmCreatePageviewTag',
            'cmCreateProductviewTag',
            'cmCreateShopAction5Tag',
            'cmDisplayShops',
            'cmCreateShopAction9Tag',
            'cmCreateOrderTag',
            'cmCreateRegistrationTag',
            'cmCreateElementTag',
            'cmCreateConversionEventTag',
            'cmCreateManualPageviewTag',
            'cmCreateManualLinkClickTag',
            'cmCreateManualImpressionTag',
            'cmCreateCustomTag',
            'cmSetupOther',
            'cmSetCurrencyCode',
            'cmDisplayShop9s',
            'cmDisplayShop5s'
        ],
        queue = [];
    
    (function init() {
        if (!isOriginSetLoaded()) {
            for (var i = 0; i < ghostFunctions.length; i++) {
                createGhostFunction(ghostFunctions[i]);
            }
            
            listenForOriginSet();
        }
    })();
    
    function isOriginSetLoaded() {
        for (var i = 0; i < ghostFunctions.length; i++) {
            if (typeof(window[ghostFunctions[i]]) !== 'function' || window[ghostFunctions[i]].isGhost) {
                return false;
            }
        }
        
        return true;
    }
    
    function createGhostFunction(ghostFunctionName) {
        window[ghostFunctionName] = function() {
            queue.push({
                functionName: ghostFunctionName,
                args: arguments
            });
        }
        
        window[ghostFunctionName].isGhost = true;
    }
    
    function listenForOriginSet() {
        setTimeout(function() {
            if (isOriginSetLoaded()) {
                delegateQueue();
            } else {
                listenForOriginSet();
            }
        }, 50);
    }
    
    function delegateQueue() {
        for (var i = 0; i < queue.length; i++) {
            window[queue[i].functionName].apply(this, queue[i].args);
        }
    }
})();

/*
 * This part is starting very first
 * Use it for loading eluminate.js 
 */
//----------------------ibmStats.event function for Tealium started---------------------------------------------//
function storeIBMStatsEvent(data,obj,linkIdentifier){
	var statsObjListString = JSON.stringify(obj).replace(/-_-/g,"---");
	var statsObjList = JSON.parse(statsObjListString);
	statsObjList.event_name = linkIdentifier;
	window.arrObjList.push(statsObjList,window.arrObjList.length);
	window.arrObjList.pop();
	window.onload = function(){
		if(typeof window.utag !== "undefined" && typeof window.utag.data !== "undefined"){
			modifySiteID = checkMarketingData();
			for(var i=0;i<window.arrObjList.length;i++){
					data = JSON.parse(JSON.stringify(window.arrObjList[i]));
					  data.event_name = window.arrObjList[i].event_name;
					  data.evPageLoadingTime = utag.data["page_loadingTime"];
					  data.evTriggerTime = utag.data["page_loadingTime"];
					  data.IbmerVal = utag.data["IBMER_value"];
					  data.evPageLocation = window.location.href.replace(/-_-/g,"---");
					  data.categoryVal = utag.data["category_id"];
					  data.cookie_sessionID = utag.data["cookie_sessionID"];
					  data.evClientID = modifySiteID;
					  //for generating product view tag
					  if(window.arrObjList[i].event_name == "ibmStatsEvent_product" && typeof window.pageViewAttributes !== "undefined"){
							var x = window.pageViewAttributes.split("-_-");
							for(var k=0; k<= x.length; k++){
								var pr_y = "productTag_a"+k;
								if(x[k] !== "undefined" || x[k] !== "")	data[pr_y] = x[k];
							}
							if(typeof window.arrObjList[i].serviceType !== "undefined") data["productTag_serviceType"] = window.arrObjList[i].serviceType;
						}
					utag.link(data);
				}
		}
	}
}

function createUtagLinkObject(obj){
	var pageLocation = window.location.href.replace(/-_-/g,"---"),
		eventTriggerTime = new Date().getTime(),
		linkIdentifier = "ibmStatsEvent_element",
		data = new Object();
	  
	if(obj.ibmConversion && obj.ibmConversion == "true"){
		if (!obj.point && obj.convtype && obj.convtype == "1") obj.point = '10';
		if (!obj.point && obj.convtype && obj.convtype == "2") obj.point = '20';
		linkIdentifier = "ibmStatsEvent_conversion";
	}else if(obj.ibmProductTag && obj.ibmProductTag == "true"){
		linkIdentifier = "ibmStatsEvent_product";
	}
	if(!obj.ibmConversion && !obj.ibmProductTag) {
		obj.ibmEvActionAttribute = obj.ibmEvAction;
		if(obj.ibmEvAction && typeof obj.ibmEvAction !== "undefined" && obj.ibmEvAction.length > 50){
			obj.ibmEvAction = obj.ibmEvAction.substring(0,22) + "..." + obj.ibmEvAction.substring(obj.ibmEvAction.length - 25, obj.ibmEvAction.length);
		}
	}
	if(typeof utag !== "undefined" && linkIdentifier !== "ibmStatsEvent_product"){
		modifySiteID = checkMarketingData();
		if(typeof window.ibm_global_data !== "undefined" && typeof ibm_global_data["Site ID"] != "undefined" && 
				typeof obj.ibmEV !== "undefined" && (obj.ibmEV.toLowerCase().indexOf("rich_media_service") !== -1 || obj.ibmEV.toLowerCase().indexOf("video player") !== -1)){
			obj.ibmEV = "VIDEO - " + modifySiteID.substring(modifySiteID.indexOf('|')+1,modifySiteID.length);
			if(obj.ibmEvAction.toLowerCase() == "start" || obj.ibmEvAction.toLowerCase() == "played"){
				obj.ibmEvName = obj.ibmEvName + " - Play";
				obj.convtype = 1;
				trackingConversionEvent(linkIdentifier,obj);
			}else if (obj.ibmEvAction.toLowerCase() == "finish" || obj.ibmEvAction.toLowerCase() == "ended"){
				obj.ibmEvName = obj.ibmEvName + " - End";
				obj.convtype = 2;
				trackingConversionEvent(linkIdentifier,obj);
			}
		}
		var statsObjListString = JSON.stringify(obj).replace(/-_-/g,"---");
		data = JSON.parse(statsObjListString);
		  data.event_name = linkIdentifier;
		  data.evPageLoadingTime = utag.data["page_loadingTime"];
		  data.IbmerVal = utag.data["IBMER_value"];
		  data.evPageLocation = pageLocation;
		  data.evTriggerTime = eventTriggerTime;
		  data.categoryVal = utag.data["category_id"];
		  data.cookie_sessionID = utag.data["cookie_sessionID"];
		  data.evClientID = modifySiteID;
		utag.link(data);
		}else{
			storeIBMStatsEvent(data,obj,linkIdentifier);
		}
}

function trackingConversionEvent(utagLinkIdentifier,obj){
	utagLinkIdentifier = "ibmStatsEvent_conversion";
	utag.link({
		  event_name : utagLinkIdentifier,
		  ibmEV : obj.ibmEV,
		  ibmEvAction : obj.ibmEvName,
		  convtype : obj.convtype,
		  evClientID : modifySiteID
	});
}

function checkMarketingData(){
	var modify_siteId = window.utag.data.concat_clientid;
	var x = window.utag.data.concat_clientid.substring(0,window.utag.data.concat_clientid.indexOf('|'));
	if(typeof window.ibm_global_data !== "undefined" && typeof ibm_global_data["Site ID"] != "undefined"){
		if(utag.data['IBMER_value'] == "1"){
			modify_siteId = x + "|" + "New_IBMER";
		}else if (window.ibm_global_data["Site ID"] !== undefined){
			modify_siteId = x + "|" + window.ibm_global_data["Site ID"];
		}
	}
	return modify_siteId;
}
//----------------------ibmStats.event function for Tealium ended---------------------------------------------//
function create_cmElement(obj){
	if(obj.ibmProductTag && obj.ibmProductTag == "true"){
		window.onload = function(){
			if (typeof (window.pageViewAttributes) == "undefined")	ibmweb.eluminate.storeTealiumPageviewData();
			if (typeof (window.pageViewAttributes) != "undefined") var productAttr = window.pageViewAttributes.split("-_-", 21).join("-_-");
			if (typeof(window.ibmweb.config.eluminate.siteID) !== "undefined" && window.ibmweb.config.eluminate.siteID.toLowerCase()== "ecom" && typeof obj.serviceType != "undefined") productAttr += "-_--_--_--_--_--_--_--_--_--_-" + obj.serviceType;
			if (typeof cmCreateProductviewTag !== 'undefined') cmCreateProductviewTag(obj.proID,obj.proName,obj.proCategory,productAttr,obj.cm_vc);
		}
	}
}

var ibmStats = ibmStats || {};
var modifySiteID = "";
window.arrObjList = new Array();
ibmStats.loaded = true;
ibmStats.event = function (obj) {
	createUtagLinkObject(obj);
	//create_cmElement(obj);
};

bindPageViewWithAnalytics = function(){
	if(typeof window.utag !== "undefined") utag.view(utag.data);
}

ibmweb.eluminate = {

	downloadTypes: ['bqy','doc','dot','exe','flv','jpg','png','mov','mp3','pdf','pps','ppt','rss','sh','swf','tar','txt','wmv','xls','xml','zip','avi','eps','gif','lwp','mas','mp4','pot','prz','rtf','wav','wma','123','odt','ott','sxw','stw','docx','odp','otp','sxi','sti','pptx','ods','ots','sxc','stc','xlsx'],
	domainList: ['.ibm.com', '.lotus.com', '.lotuslive.com', '.cognos.com', '.webdialogs.com', '.jazz.net', '.servicemanagementcenter.com','.xtify.com','.ibmcloud.com','.ibmdw.net','.bluemix.net','.smartercitiescloud.com'],
	domainBlacklist: ".ibm.com,.mitre.org,.learnquest.com",
	
	// creating QueryString variable
	create_QueryString: function() {
		var query = window.location.search.substring(1);
		
		try {
			window.QueryString = dojo.queryToObject(query);
		} catch (e) {
			window.QueryString = {};
		}
	},

	domainTest: function(host) {
		if (host.length > 0) {
			host = host.toLowerCase();

			if (host == window.location.hostname.toLowerCase() || dojo.indexOf(this.domainList, host) !== -1) return true;

			for (var i = 0; i < this.domainList.length; i++) {
				if (host.search(this.domainList[i]) != -1) return true;
			}
		}
		return false;
	},

	match: function(pth) {
		var result = false,
			type = pth.substring(pth.lastIndexOf(".") + 1, pth.length);

		if (dojo.indexOf(this.downloadTypes, type) !== -1) result = true;

		return result;
	},

	pause: function(ms) {
		var date = new Date(),
			curDate = null;

		do curDate = new Date(); while(curDate - date < ms);
	},

	/*
	findElm: function(e, tag) {
			var elm = dojo.query(e.target || e.srcElement);

			if (typeof elm[0] === 'undefined') return null;

			while (elm[0].tagName.toLowerCase() !== tag) {
				if (elm.parent(tag).length == 1) elm = elm.parent(tag);
				else return elm;
			}
			return elm[0];
	},
	*/
	findElm: function(e, tag) {
		var elm = dojo.query(e.target || e.srcElement);

		if (typeof elm[0] === 'undefined') return null;

		elmNode = elm[0];

		var result = elmNode;
		
		while (typeof elmNode.parentNode !== 'undefined') {
			elmNode = elmNode.parentNode;
			if (elmNode == null) break;
			if (typeof elmNode.tagName !== 'undefined' && elmNode.tagName.toLowerCase() === tag) {result = elmNode; break}
		}

		return result;
	},
    
	download_and_offset_tracking: function(e) {

		if (typeof e == "undefined") return;

		var elm = this.findElm(e, 'a');

		if (typeof elm === 'undefined' || elm == null) return;

		//var pageid = (typeof(window.digitalData) !== "undefined" && typeof(window.digitalData.page) !== "undefined" && typeof(window.digitalData.page.pageID) !== "undefined")? window.digitalData.page.pageID : "";

		if ((typeof elm.tagName !== 'undefined' && elm.tagName.toLowerCase() == 'a') && !!elm.href) this.tracking_core(e, elm, 'normalClick');
	},
	
	left_click_tracking: function(e){
		var ev = (e.target)? e.target : ((e.srcElement)? e.srcElement : e.delegateTarget); //added to prevent duplicate element tag while ibmStats.event presents
		if (typeof ev !== 'undefined' || ev != null) {
			if(navigator.userAgent.toLowerCase().indexOf('firefox') > -1)
			{ 
				var switcher = false;
				for (var att in ev.attributes)
				{	
					if (ev.attributes[att].name == 'onclick' && ev.attributes[att].value.indexOf('ibmStats.event') > -1 )
					{
						switcher = true;
						break;
					}
				}
				if (switcher != true) 
				{
					this.download_and_offset_tracking(e);
				}
			}
			else
			{
				var statsEvent = (ev.getAttribute("onclick") !== null) ? (ev.getAttribute("onclick").indexOf("ibmStats.event")) : -1;
				if(statsEvent === -1) 
				{
					this.download_and_offset_tracking(e);
				}

			}
		}
	}, 
	
	right_click_tracking: function(e) {

		if (typeof e == "undefined") return;
		
		var btn = e.which || e.button;

		if ((btn !== 1 ) || (navigator.userAgent.indexOf("Safari") !== -1)) {

			var elm = this.findElm(e, 'a');

			if (typeof elm === 'undefined' || elm == null) return;

			//var pageid = (typeof(window.digitalData) !== "undefined" && typeof(window.digitalData.page) !== "undefined" && typeof(window.digitalData.page.pageID) !== "undefined")? window.digitalData.page.pageID : "";

			if ((typeof elm.tagName !== 'undefined' && elm.tagName.toLowerCase() == 'a') && !!elm.href) this.tracking_core(e, elm, 'rightClick');
		}
	},
	
	//function to check if the link calls www megamenu or social toolbar
    check_megamenu_element : function(el){
		if (typeof (el) !== 'undefined'){
	    	do {
	    		if (el.id == "ibm-menu-links" || el.id == "ibm-common-menu" || el.id == "ibm-social-tools") {
	    	      	//(el.className == "ibm-media")
	    	      	return true;
	    	    }
	    		el = el.parentElement || el.parentNode;
	    	  } while ((el !== null) && (el.parentElement || el.parentNode))
		}
    	return false;
    },
	
	tracking_core: function(e, elm, type) { // for both click type
    	var hostName = elm.hostname? (elm.hostname.split(":")[0]) : "",
			fullURL = escape(elm.href),
			qry = elm.search? elm.search.substring(elm.search.indexOf("?") + 1, elm.search.length) : "",
			p = dojo.queryToObject(qry),
			vparam = 'none',
			prtcl = elm.protocol || "",
			evAction = (elm.protocol == "ftp:")?  fullURL.substr(8) : ((elm.protocol == "https:") ? fullURL.substr(10) : fullURL.substr(9)),
			evid = (elm.protocol == "ftp:")?  elm.href.substr(6) : ((elm.protocol == "https:") ? elm.href.substr(8) : elm.href.substr(7)),
			evLinkTitle = (navigator.appVersion.indexOf("MSIE") != -1) ? elm.innerText.trim() : elm.textContent.trim(),
			pageid = "";
			
			if(evLinkTitle.length > 256) evLinkTitle = evLinkTitle.substring(0,125) + "..." + evLinkTitle.substring(evLinkTitle.length - 128, evLinkTitle.length);
			
			//replace -_- from evAction if exists 
            if(evAction.indexOf('-_-') != -1){
            	evAction = evAction.replace(/-_-/g,"---");
            }

        if (typeof (window.digitalData) != "undefined" && typeof (window.digitalData.page) != "undefined") {
        	if(typeof (window.digitalData.page.pageInfo) != "undefined" && typeof (window.digitalData.page.pageInfo.pageID) != "undefined"){//for new DDO structure
        		pageid = window.digitalData.page.pageInfo.pageID;
        	}else if(typeof (window.digitalData.page.pageID) != "undefined"){
        		pageid = window.digitalData.page.pageID;
        	}
        	var currentdate = new Date(),
        		pageLocation = window.location.href.replace(/-_-/g,"---");
        	if (typeof (window.utag) != "undefined" && typeof (window.utag.data) != "undefined") {
        		pageid = pageid + "-_--_--_--_--_--_-" + window.utag.data.page_loadingTime + "-_-" + pageLocation + "-_-" +  currentdate.getTime() + "-_-" + window.utag.data.IBMER_value;
    		}else{
    			window.NTPT_IBMer = (String(document.cookie).match(/(^| )(w3ibmProfile|w3_sauid|PD-W3-SSO-|OSCw3Session|IBM_W3SSO_ACCESS)=/)) ? 1 : 0;
    			pageid = pageid + "-_--_--_--_--_--_-" + window.loadingTime + "-_-" + pageLocation + "-_-" +  window.loadingTime + "-_-" + window.NTPT_IBMer;
    		}
        }
		        
		if (typeof p.attachment !== 'undefined') vparam = p.attachment;
		if (typeof p.FILE !== 'undefined') vparam = p.FILE;
		if (typeof p.attachmentName !== 'undefined') vparam = p.attachmentName;

		var download_param = vparam.toLowerCase(),
			download_uri = elm.pathname.toLowerCase(),
			megamenuElement = this.check_megamenu_element(elm);

		if (evid.length > 50) evid = evid.substring(0,22) + "..." + evid.substring(evid.length - 25, evid.length);
		var optionalAttribute = evLinkTitle+'-_-null-_-null-_-null-_-'+evAction.toLowerCase()+'-_-'+evLinkTitle+'-_-null-_--_--_-'+pageid;
		//element attribute for Inside Sales
		if(typeof(window.ibmweb.config.eluminate.siteID) !== "undefined" && (window.ibmweb.config.eluminate.siteID.substring(0,3)).toLowerCase() == "ins"){
			if(String(dojo.query("meta[name='PopID']").attr("content"))) optionalAttribute = optionalAttribute + "-_-" + String(dojo.query("meta[name='PopID']").attr("content"));
		}
		
		// download_tracking and page_click
		if (megamenuElement == false && this.domainTest(hostName)) {

			if (this.match(download_uri) || this.match(download_param)) {
				var ttl = "",
					text = document.all? elm.innerText : elm.text,
					img = this.findElm(e, 'img'),
					coremetricsParam = '';

				if (img.alt) ttl = img.alt;
				else if (text) ttl = text;
				else if (elm.innerHTML) ttl = elm.innerHTML;

				if (vparam == "none") {
					
					coremetricsParam = evAction.toLowerCase() + '-_-' + optionalAttribute;
					
					if (typeof cmCreateElementTag !== 'undefined') cmCreateElementTag(evid.toLowerCase(), 'download', 'download'+ '-_-' + coremetricsParam);

					//this.pause(200);

				} else {

					coremetricsParam = download_param + '-_-' + optionalAttribute;
					
					if (typeof cmCreateElementTag !== 'undefined') cmCreateElementTag(download_param, 'download', 'download'+ '-_-' + coremetricsParam);

					//this.pause(200);
				}

			} else {

				//var pageClickParams = 'page click' + '-_-' + evAction + '-_-' + evLinkTitle + '-_-null-_-null-_-null-_-' + evAction.toLowerCase() + '-_-' + evLinkTitle + '-_-null-_-';
				
				/*pageClickParams += (typeof p.lnk !== 'undefined')? p.lnk + '-_-' : '-_-';
				pageClickParams += (typeof p.lm !== 'undefined')? p.lm + '-_-' : '-_-';
				pageClickParams += (typeof p.lot !== 'undefined')? p.lot + '-_-' : '-_-';
				pageClickParams += (typeof p.lsot !== 'undefined')? p.lsot + '-_-' : '-_-';

				if (typeof p.lpg !== 'undefined') pageClickParams += p.lpg;
				
				pageClickParams += '-_--_-' + pageid;*/
				
				var pageClickParams = 'page click' + '-_-' + evAction + '-_-' + optionalAttribute;

				if (typeof cmCreateElementTag !== 'undefined') cmCreateElementTag(evid.toLowerCase(), 'page click', pageClickParams);

				//this.pause(200);
			}
		}

		// offsite_tracking
		if (megamenuElement == false && ((hostName.length > 0) && (prtcl.indexOf("http") == 0 || prtcl.indexOf("mailto") == 0) && (!this.domainTest(hostName)))) {

			if (typeof cmCreateElementTag !== 'undefined') cmCreateElementTag(evid.toLowerCase(), 'external link', 'external link' + '-_-' + evAction + '-_-' + optionalAttribute);

			//this.pause(200);
		}
	},
	
	//Limit assignment of WTMSite from WTMCategory(specially for too many category Id in IWM page)
	checkRestrictCategory : function(wtmCategoryId){
		var selectedIWMCategory = ['GBS','GTS','STG','SWG','CSUITE','DW','ESD','IND','MID','MM','PPW','RATLE'],
		    noCategory = "";
		if(wtmCategoryId.indexOf('-') !== -1){
			var selectedPart = wtmCategoryId.split('-')[0];
    		for (var i = 0; i < selectedIWMCategory.length; i++) {
    			if (selectedPart.toUpperCase() === selectedIWMCategory[i]) {
    				return selectedPart;
    			}
    		}
		}
		if(wtmCategoryId.toLowerCase() == "cuf04") noCategory = wtmCategoryId;
		return noCategory;
	},

	utilstatsHelper: function(e) {
		// check if builder has specified page title already
		if(!e.ibmEvLinkTitle && !e.ibmEvLinktitle){
			// no, so let's get it and add
			// get title of this page
			var h1Element = dojo.query('h1:first');
			if(h1Element.length > 0 && h1Element[0].innerHTML){
				// mixin with object provided by builder
				dojo.mixin(e, { 'ibmEvLinkTitle': h1Element[0].innerHTML });
			}
		}
		
		if (!e.ibmEvGroup) e.ibmEvGroup = 'null';
		if (!e.ibmEvName) e.ibmEvName = 'null';
		if (!e.ibmEvModule) e.ibmEvModule = 'null';
		if (!e.ibmEvSection) e.ibmEvSection = 'null';
		if (!e.ibmEvTarget) e.ibmEvTarget = 'null';
		if (!e.ibmEvFileSize) e.ibmEvFileSize = 'null';
		if (!e.ibmEvLinkTitle) e.ibmEvLinkTitle = 'null';

		// and now just call ibmStats.event()
		ibmStats.event(e);
	},
     
	storeTealiumPageviewData : function(){
		if (typeof (window.utag) != "undefined") {
			var arr = new Array();
			window.pageViewAttributes = "";
			var x = JSON.stringify(window.utag.sender).split(/[}]/);
			for (var i=0;i<x.length;i++){
				var firstPart = x[i].split('{')[0],
					lastPart = x[i].split('{')[1];
				if (firstPart.indexOf("map") !== -1 && typeof lastPart !== "undefined") {
					var arr1 = lastPart.split(',');
					for (var j=0;j<arr1.length;j++){
						if (typeof arr1[j].split(':')[1] !== "undefined" && arr1[j].split(':')[1].indexOf('PageviewTag_pv_a') !== -1){
							var a = arr1[j].split(':')[0].replace(/[""]/g,''),
								k = arr1[j].split(':')[1].substring(17,arr1[j].split(':')[1].length-1);
							if (typeof utag.data[a] !== "undefined" && (arr[k] == "" || arr[k] == undefined)) arr[k] = utag.data[a];
							else if (typeof utag.data[a] !== "undefined" && a.indexOf('meta.') !== -1) arr[k] = utag.data[a];//to prioritize the meta tag values over DDO
						}
					}
				}
			}
			for(var i=1;i<=arr.length;i++){
				window.pageViewAttributes += arr[i] + "-_-";
			}
		}
	},
	
	init: function() {
		//if (window.location.href.indexOf('.ibm.com/support/servicerequest') >= 0) return;

		var _this = this,
			_conf = ibmweb.config.eluminate;

		_conf.enabled = true;


		/* TEALIUM IMPLEMENTATION - START */

		(function(a,b,c,d) {
			a = '//tags.tiqcdn.com/utag/ibm/main/prod/utag.js';
			b = document;
			c = 'script';
			d = b.createElement(c);
			d.src = a;
			d.type = 'text/java' + c;
			d.async = true;
			a = b.getElementsByTagName(c)[0];
			a.parentNode.insertBefore(d,a);
			d.onload = function(){
				if(typeof window.utag !== "undefined"){
					_conf.siteID = window.utag.data["siteID_value"];
					if(_conf.siteID == "ECOM"){
						window.cmTagQueue.push(['cmSetupNormalization', 'krypto-_-krypto']);	
					}
				}
			}
		})();

		/* TEALIUM IMPLEMENTATION - END */


		// set QueryString
		this.create_QueryString();

		// set WebAnalitics
		//if (typeof(window.WebAnalytics) == 'undefined') window.WebAnalytics = {Page: {PageIdentifier: window.location.href}};

		// set digitalData
		if (typeof(window.digitalData) == 'undefined') window.digitalData = {};

		// set siteID from meta IBM.WTMSite
		/*if (typeof _conf.siteID === "undefined") {_conf.siteID = String(dojo.query("meta[name='IBM.WTMSite']").attr("content"));}

		// set siteID from meta WTMSite
		if(_conf.siteID.length == 0) {_conf.siteID = String(dojo.query("meta[name='WTMSite']").attr("content"));}
		
		//set siteID from meta IBM.WTMCategory
        if (_conf.siteID.length == 0 && String(dojo.query("meta[name='IBM.WTMCategory']").attr("content")) != null ){
        	if (String(dojo.query("meta[name='IBM.WTMCategory']").attr("content")).substring(0, 5) == "SOFDC") {
        		_conf.siteID = "DEVWRKS";
        	}else{
        		_conf.siteID = this.checkRestrictCategory(String(dojo.query("meta[name='IBM.WTMCategory']").attr("content")));
        	}
        }
        
		// set siteID from digitalData siteID or categoryID
		if(_conf.siteID.length == 0 && typeof digitalData.page !== "undefined") {
			//for old DDO structure
			if (typeof digitalData.page.site !== "undefined" && typeof digitalData.page.site.siteID !== "undefined") {_conf.siteID = digitalData.page.site.siteID;}
			//for new DDO structure
			if (_conf.siteID.length == 0 && typeof digitalData.page.pageInfo !== "undefined" && typeof digitalData.page.pageInfo.ibm !== "undefined" && typeof digitalData.page.pageInfo.ibm.siteID !== "undefined") {
				_conf.siteID = digitalData.page.pageInfo.ibm.siteID;
	        }
			
			//for old DDO structure
			if (_conf.siteID.length == 0 && typeof digitalData.page.category !== "undefined" && typeof digitalData.page.category.categoryID !== "undefined") {
				if (digitalData.page.category.categoryID.substring(0, 5) == "SOFDC") {
					_conf.siteID = "DEVWRKS";
            	}else{
            		_conf.siteID = this.checkRestrictCategory(digitalData.page.category.categoryID);
            	}
			}
			//for new DDO structure
			if (_conf.siteID.length == 0 && typeof digitalData.page.category !== "undefined" && typeof digitalData.page.category.primaryCategory !== "undefined") {
				if (digitalData.page.category.primaryCategory.substring(0, 5) == "SOFDC") {
					_conf.siteID = "DEVWRKS";
            	}else{
            		_conf.siteID = this.checkRestrictCategory(digitalData.page.category.primaryCategory);
            	}
			}       
		}
		//GBS site id change
    	if (window.location.href.toLowerCase().indexOf("935.ibm.com/services/") !== -1){
    		if ((window.location.pathname.toLowerCase().indexOf("/gbs/") !== -1) || (window.location.pathname.toLowerCase().indexOf("/business-consulting/") !== -1)){
    			_conf.siteID = "GBS";
    		}
    	}else if ((window.location.href.toLowerCase().indexOf("935.ibm.com/industries/") !== -1) || (window.location.href.toLowerCase().indexOf("06.ibm.com/industries/jp/") !== -1)){
    		_conf.siteID = "INDUSTRIES";
    	} 
		// set siteID on default value
		if(_conf.siteID.length == 0) {_conf.siteID = "IBMTESTWWW";}
		
		_conf.cmSetClientID.id = _conf.CID + "|" + _conf.siteID;*/
		
		// set cmTagQueue
		if (typeof(window.cmTagQueue) == 'undefined')  window.cmTagQueue = [];

		//if the site id starts or ends with "test" set the client as 802
		/*if(_conf.siteID.substring(0, 4).toLowerCase() == "test" || _conf.siteID.substring(_conf.siteID.length-4, _conf.siteID.length).toLowerCase() == "test"){
			window.cmTagQueue.push(['cmSetClientID', '80200000|'+_conf.siteID, false, 'testdata.coremetrics.com', _conf.cmSetClientID.cookieDomain]);
		}else{
			window.cmTagQueue.push(['cmSetClientID', _conf.cmSetClientID.id, _conf.cmSetClientID.managedFirstParty, _conf.cmSetClientID.dataCollectionDomain, _conf.cmSetClientID.cookieDomain]);
		}*/
		window.cmTagQueue.push(['cmSetupOther', {"cm_JSFEAMasterIDSessionCookie": true}]);
		
		/*if ((_conf.siteID !== "undefined") && (_conf.siteID.toLowerCase() == "bluemix" || _conf.siteID.toLowerCase() == "cloudexchange" || _conf.siteID.toLowerCase() == "ecom")) {
			window.cmTagQueue.push(['cmSetupCookieMigration', true, true, this.domainList]);
		}*/
		//cookie migration from IBM to non IBM pages
		if(typeof (document.domain) !== 'undefined' && document.domain.indexOf('ibm.com') !== -1){
			window.cmTagQueue.push(['cmSetupCookieMigration', true, true, null, this.domainBlacklist]);
		}

		// loading eluminate
		(function() {
			/*var script = document.createElement('script');
			script.setAttribute('type', 'text/javascript');
			script.setAttribute('src', '//libs.coremetrics.com/eluminate.js');
			document.getElementsByTagName('head')[0].appendChild(script);*/
			
			window.loadingTime = new Date().getTime();
			window.eluminateLoaded = true;
		})();

		// coremetrics event functions
		dojo.addOnLoad(function(){
			//if(_conf.siteID != "DWNEXT"){//to block event tracking for site id "DWNEXT"
				// for download and offset tracking
				//dojo.connect(dojo.body(),"onclick",ibmweb.eluminate,ibmweb.eluminate.download_and_offset_tracking);
				/*dojo.connect(dojo.body(), "onmousedown", function(e) {
	          		if ((typeof(e.which) !== 'undefined' && e.which == 1) || (typeof(e.button) !== 'undefined' && e.button == 0)) {
	          			_this.left_click_tracking(e);
	          		}
				});
				// for right click tracking
				dojo.connect(dojo.body(), "onmousedown", function(e) {
					if ((typeof(e.which) !== 'undefined' && e.which == 3) || (typeof(e.button) !== 'undefined' && e.button == 2)) _this.right_click_tracking(e);
				});
	
				// for middle click tracking
				dojo.connect(dojo.body(), "onmouseup", function(e) {
					if ((typeof(e.which) !== 'undefined' && e.which == 2) || (typeof(e.button) !== 'undefined' && e.button == 4)) _this.download_and_offset_tracking(e);
				});*/
			//}
			//function call to store all page view attributes
			 if (window.addEventListener) {
		      	  window.addEventListener('load', _this.storeTealiumPageviewData, false);
		      }
		      else if (window.attachEvent) {
		      	  window.attachEvent('onload', _this.storeTealiumPageviewData);
		      }
		});
	}
};

//if (ibmweb.config.config == 'www' && navigator.userAgent.toLowerCase().indexOf('msie') == -1) {
if (ibmweb.config.config == 'www') {

	if (navigator.platform.search('AIX') < 0) { // we disabling coremetrics for AIX server

		cmSetClientID = function(){};
		if (typeof(window.eluminate_enabled) !=='undefined') {
			// we search if this variable is set on false
			if (!window.eluminate_enabled) {/*do nothing*/}
			else ibmweb.eluminate.init();
		} else {
			// we are enabled for all pages
			ibmweb.eluminate.init();
		}
	}
}