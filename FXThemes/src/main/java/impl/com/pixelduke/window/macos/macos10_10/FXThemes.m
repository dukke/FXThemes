//
//  FXThemes.m
//  FXThemes
//
//  Created by Dea, Carl on 2/18/24.
//
#import "FXThemes.h"
#import "Foundation/Foundation.h"
#import "AppKit/AppKit.h"

void setAppearanceByName(void* windowptr, NSString *nsAppearanceName) {
    long myLongValue = (long)windowptr;
    NSLog(@"setAppearanceByName() Native window pointer (long) = %ld", myLongValue);

    // Converting C string into a NSString
    //NSString *myNSString = [NSString stringWithUTF8String:nsAppearanceName];
    NSLog(@"nsAppearanceName = %@", nsAppearanceName);

    // Casting pointer into a NSWindow pointer
    NSWindow* win = (__bridge NSWindow*)(windowptr);
    NSLog(@"NSWindow = %@", win);
    
    // Do things on the native NSApp thread.
    dispatch_async(dispatch_get_main_queue(), ^{
        NSView* hostView = win.contentView;

        if (hostView != nil && hostView.subviews.count) {
            NSLog(@"  Before NSVisualEffectView creation subviews.count = %lu", hostView.subviews.count);

            NSMutableArray *mutableSubviews = [hostView.subviews mutableCopy];
            for (NSView *subview in [mutableSubviews copy]) {
                // Perform some condition to determine if the subview should be removed
                if ([subview isKindOfClass:[NSVisualEffectView class]]) {
                    [subview removeFromSuperview];
                    [mutableSubviews removeObject:subview];
                }
            }

            // Now, mutableSubviews contains the modified array with items removed
            [hostView setSubviews:mutableSubviews];
            
            // iterate over sub views
            NSArray *subviews = [hostView subviews];
            NSUInteger count = [subviews count];

            for (NSUInteger i = 0; i < count; i++) {
                NSView *subview = [subviews objectAtIndex:i];
                
                // Perform operations on each subview
                NSLog(@" subview (NSView): %@", subview);
            }
            
            // Get JavaFX Scene root node
            NSView* jfxView = hostView.subviews[0];
            
            /* Swift
             let visualEffect = NSVisualEffectView()
             visualEffect.blendingMode = .behindWindow
             visualEffect.state = .active
             visualEffect.material = .dark
             window?.contentView = visualEffect
             */
            NSVisualEffectView *vfxView = [[NSVisualEffectView alloc] initWithFrame:[win.contentView bounds]];

            [vfxView setAppearance:[NSAppearance appearanceNamed:nsAppearanceName]];
            [vfxView setBlendingMode:NSVisualEffectBlendingModeBehindWindow];
            //[vfxView setState:NSVisualEffectStateActive];
            [vfxView setMaterial:NSVisualEffectMaterialUnderWindowBackground];
            
            // make sure javafx layer is not opaque
            [vfxView setAutoresizingMask: (NSViewWidthSizable|NSViewHeightSizable)];
            [hostView addSubview: vfxView positioned: NSWindowBelow relativeTo: jfxView];
        }
    });
}

@implementation FXThemes

@end
