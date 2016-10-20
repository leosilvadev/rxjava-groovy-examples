package rx.spring.handler

import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.context.request.async.WebAsyncUtils
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.method.support.ModelAndViewContainer
import rx.Observable

class ObservableReturnValueHandler implements HandlerMethodReturnValueHandler {

	public boolean supportsReturnType(MethodParameter returnType) {
		Class parameterType = returnType.getParameterType()
		Observable.isAssignableFrom(parameterType)
	}

	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
		if(returnValue == null) {
			mavContainer.setRequestHandled(true)
			return
		}

		final DeferredResult deferredResult = new DeferredResult()
		returnValue.subscribe({ result ->
			deferredResult.setResult(result)
		}, { errors ->
			deferredResult.setErrorResult(errors)
		})

		WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult, mavContainer)
	}
}