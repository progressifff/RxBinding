package com.example.rxbinding.rx.binding

interface RelatedType

interface StateRelatedType : RelatedType
interface CommandRelatedType : RelatedType

object ViewModelRelatedType : StateRelatedType, RelatedType, CommandRelatedType
object ViewRelatedType : StateRelatedType, RelatedType, CommandRelatedType